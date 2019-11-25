package com.epam.tc.encoder;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author alex raby
 * @version 1.0
 * class containing a method for encrypting data
 */
public class PassEncoder {

  /**
   * the method encrypts the string using {@link DigestUtils#md5Hex(String)}
   * 128-bit hashing algorithm
   * @param unencrypted - string to be encrypted
   * @return encrypted String size 32 byte
   */
  public String md5Apache(String unencrypted) {
    String encrypted = DigestUtils.md5Hex(unencrypted);
    return encrypted;
  }
}
