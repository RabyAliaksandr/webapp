package com.epam.tc.tag;

import com.epam.tc.entity.Consultation;
import com.epam.tc.entity.Payment;
import com.epam.tc.entity.PaymentCard;
import com.epam.tc.entity.User;
import com.epam.tc.service.PaymentService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * The type Score table custom tag.
 *
 * @author alex raby
 * @version 1.0
 * extends {@link TagSupport}
 */
public class ScoreTableTag extends TagSupport {

  private String local;

  /**
   * Getter for property 'local'.
   *
   * @return Value for property 'local'.
   */
  public String getLocal() {
    return local;
  }

  /**
   * Setter for property 'local'.
   *
   * @param local Value to set for property 'local'.
   */
  public void setLocal(String local) {
    this.local = local;
  }

  /**
   * {@inheritDoc}
   */

  @Override
  public int doStartTag() throws JspTagException {
    ScoreTableLocal scoreTableLocal = new ScoreTableLocal(local);
    try {
      JspWriter out = pageContext.getOut();
      out.write("<table>");
      out.write("<thead><tr><th scope='col'>");
      out.write("No" + "</th>");
      out.write("<th>" + scoreTableLocal.getLocalName("label.userName") + "</th>" +
              "<th>" + scoreTableLocal.getLocalName("label.userSurname") + "</th>" +
              "<th>" + scoreTableLocal.getLocalName("label.price") + "</th>" +
              "<th>" + scoreTableLocal.getLocalName("label.paymentDate") + "</th>" +
              "<th>" + scoreTableLocal.getLocalName("label.cardNumbers") + "</th>" + "</tr>");
      out.write("<tbody>");
      PaymentService paymentService = ServiceFactory.getPaymentService();
      List<Payment> payments = paymentService.findAllPayments();
      int count = 1;
      for (Payment payment : payments) {
        out.write("<tr><td>");
        out.write(String.valueOf(count));
        out.write("</td><td>");
        out.write(payment.getUser().getName());
        out.write("</td><td>");
        out.write(payment.getUser().getSurname());
        out.write("</td><td>");
        out.write(String.valueOf(payment.getConsultation().getPrice()));
        out.write("</td><td>");
        out.write(String.valueOf(payment.getConsultation().getDate()));
        out.write("</td><td>");
        out.write(String.valueOf(payment.getPaymentCard().getNumber()));
        count++;
      }
    } catch (IOException e) {
      throw new JspTagException(e.getMessage());
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return EVAL_BODY_INCLUDE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int doEndTag() throws JspTagException {
    try {
      pageContext.getOut().write("</td></tr></tbody></table>");
    } catch (IOException e) {
      throw new JspTagException(e.getMessage());
    }
    return EVAL_PAGE;
  }
}