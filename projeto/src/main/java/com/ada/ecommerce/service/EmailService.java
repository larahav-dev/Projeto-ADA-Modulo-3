package com.ada.ecommerce.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * Serviço responsável pelo envio de e-mails de notificação em formato HTML.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    /**
     * Envia um e-mail com conteúdo HTML diretamente (sem uso de template).
     *
     * @param destinatario endereço de e-mail do cliente
     * @param assunto título do e-mail
     * @param corpoHtml conteúdo HTML da mensagem
     */
    public void notificarHtml(String destinatario, String assunto, String corpoHtml) {
        validarDestinatario(destinatario);

        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true); // true = HTML

            mailSender.send(mensagem);
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail HTML: " + e.getMessage());
        }
    }

    /**
     * Envia um e-mail baseado em um template Thymeleaf.
     *
     * @param destinatario endereço de e-mail do cliente
     * @param assunto título do e-mail
     * @param nome nome do cliente
     * @param pedidoId número do pedido
     * @param valorTotal valor total do pedido
     */
    public void notificarPedidoConfirmadoComTemplate(String destinatario, String assunto, String nome, Long pedidoId, String valorTotal) {
        validarDestinatario(destinatario);

        try {
            Context context = new Context();
            context.setVariable("nome", nome);
            context.setVariable("pedidoId", pedidoId);
            context.setVariable("valorTotal", valorTotal);

            String corpoHtml = templateEngine.process("email/pedido-confirmado", context);

            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true);

            mailSender.send(mensagem);
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail com template: " + e.getMessage());
        }
    }

    /**
     * Valida se o destinatário do e-mail é válido.
     *
     * @param destinatario e-mail do destinatário
     */
    private void validarDestinatario(String destinatario) {
        if (destinatario == null || destinatario.isBlank()) {
            throw new IllegalArgumentException("Destinatário do e-mail não pode estar vazio.");
        }
    }
}