package domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Pedido {
    private final String id;
    private final Cliente cliente;
    private final LocalDateTime criadoEm;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    private final Map<Integer, ItemPedido> itens = new LinkedHashMap<>();

    public Pedido(String id, Cliente cliente) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id do pedido é obrigatório.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório.");
        }
        this.id = id;
        this.cliente = cliente;
        this.criadoEm = LocalDateTime.now();
        this.statusPedido = StatusPedido.ABERTO;
        this.statusPagamento = StatusPagamento.NAO_INICIADO;
    }

    public void adicionarItem(Produto produto, int qtd, BigDecimal precoUnitario) {
        assertAberto();
        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório.");
        }
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (precoUnitario == null || precoUnitario.signum() <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
        }

        ItemPedido existente = itens.get(produto.getId());
        if (existente == null) {
            itens.put(produto.getId(), new ItemPedido(produto, qtd, precoUnitario));
        } else {
            existente.alterarQuantidade(existente.getQuantidade() + qtd);
        }
    }

    public void removerItem(int produtoId) {
        assertAberto();
        if (itens.remove(produtoId) == null) {
            throw new IllegalArgumentException("Item não encontrado no pedido.");
        }
    }

    public void alterarQuantidade(int produtoId, int novaQuantidade) {
        assertAberto();
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        ItemPedido item = itens.get(produtoId);
        if (item == null) {
            throw new IllegalArgumentException("Item não encontrado no pedido.");
        }
        item.alterarQuantidade(novaQuantidade);
    }

    public BigDecimal total() {
        return itens.values().stream()
                .map(ItemPedido::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void finalizar() {
        assertAberto();
        if (itens.isEmpty()) {
            throw new IllegalStateException("Pedido precisa ter ao menos um item para ser finalizado.");
        }
        if (total().signum() <= 0) {
            throw new IllegalStateException("O valor total do pedido deve ser maior que zero.");
        }
        this.statusPagamento = StatusPagamento.AGUARDANDO_PAGAMENTO;
    }

    public void pagar() {
        if (statusPagamento != StatusPagamento.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("Pagamento só pode ser realizado quando o status for 'AGUARDANDO_PAGAMENTO'.");
        }
        this.statusPagamento = StatusPagamento.PAGO;
    }

    public void entregar() {
        if (statusPagamento != StatusPagamento.PAGO) {
            throw new IllegalStateException("Entrega só pode ser realizada após o pagamento.");
        }
        this.statusPedido = StatusPedido.FINALIZADO;
    }

    private void assertAberto() {
        if (statusPedido != StatusPedido.ABERTO) {
            throw new IllegalStateException("Alterações só são permitidas quando o pedido está 'ABERTO'.");
        }
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public StatusPedido getStatusPedido() { return statusPedido; }
    public StatusPagamento getStatusPagamento() { return statusPagamento; }
    public Collection<ItemPedido> getItens() { return Collections.unmodifiableCollection(itens.values()); }
}
