package com.ns.cjcq.security.validator;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    //验证码图片
    private BufferedImage image;

    //随机数
    private String code;

    //验证码过期时间
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    //expireInt：传入的验证码有效期
   public ImageCode(BufferedImage image, String code,int expireInt){
       this.image = image;
       this.code = code;
       this.expireTime = LocalDateTime.now().plusSeconds(expireInt);
   }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
