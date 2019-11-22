package com.epam.tc.encoder;

import org.apache.commons.codec.digest.DigestUtils;



public class PassEncoder {

  public String md5Apache(String st) {
    String md5Hex = DigestUtils.md5Hex(st);
    return md5Hex;
  }
}
