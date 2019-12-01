package com.epam.tc.tag;

import com.epam.tc.entity.Payment;
import com.epam.tc.service.PaymentService;
import com.epam.tc.service.ServiceException;
import com.epam.tc.service.ServiceFactory;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * The type Score table tag.
 */
public class ScoreTableTag extends TagSupport {

  private String name;
  private String surName;
  private String price;
  private String date;
  private String cardNumber;


  /**
   * Getter for property 'name'.
   *
   * @return Value for property 'name'.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for property 'name'.
   *
   * @param name Value to set for property 'name'.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for property 'surName'.
   *
   * @return Value for property 'surName'.
   */
  public String getSurName() {
    return surName;
  }

  /**
   * Setter for property 'surName'.
   *
   * @param surName Value to set for property 'surName'.
   */
  public void setSurName(String surName) {
    this.surName = surName;
  }

  /**
   * Getter for property 'price'.
   *
   * @return Value for property 'price'.
   */
  public String getPrice() {
    return price;
  }

  /**
   * Setter for property 'price'.
   *
   * @param price Value to set for property 'price'.
   */
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Getter for property 'date'.
   *
   * @return Value for property 'date'.
   */
  public String getDate() {
    return date;
  }

  /**
   * Setter for property 'date'.
   *
   * @param date Value to set for property 'date'.
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Getter for property 'card_number'.
   *
   * @return Value for property 'card_number'.
   */
  public String getCardNumber() {
    return cardNumber;
  }

  /**
   * Setter for property 'card_number'.
   *
   * @param cardNumber Value to set for property 'card_number'.
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public int doStartTag() throws JspTagException {
    try {
      JspWriter out = pageContext.getOut();
      out.write("<table>");
      out.write("<thead><tr><th scope='col'>");
      out.write("No" + "</th>");
      out.write("<th>" + name + "</th>" +
              "<th>" + surName + "</th>" +
              "<th>" + price + "</th>" +
              "<th>" + date + "</th>" +
              "<th>" + cardNumber + "</th>" + "</tr>");
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
//        out.write("</td><td>");

      }

    } catch (IOException e) {
      throw new JspTagException(e.getMessage());
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return EVAL_BODY_INCLUDE;
  }

//  @Override
//  public int doAfterBody() throws JspTagException {
//    if (rows-- > 1) {
//      try {
//        pageContext.getOut().write("</td></tr><tr><td>");
//      } catch (IOException e) {
//        throw new JspTagException(e.getMessage());
//      }
//      return EVAL_BODY_AGAIN;
//    } else {
//      return SKIP_BODY;
//    }
//  }

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