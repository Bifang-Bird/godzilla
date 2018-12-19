package com.godzilla.cn.godzilla.utils;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MD5摘要工具
 *  @author xkfeng@iflytek.com
 *  @lastModified
 *  @history
 *  @date 2018-01-29
 */
public class MD5Util {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final String encodingAlgorithm="MD5";

    private static String characterEncoding="utf-8";

    public static String encode(final String password) {
        if (password == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest
                    .getInstance(encodingAlgorithm);

            if (StringUtils.hasText(characterEncoding)) {
                messageDigest.update(password.getBytes(characterEncoding));
            } else {
                messageDigest.update(password.getBytes());
            }


            final byte[] digest = messageDigest.digest();

            return getFormattedText(digest);
        } catch (final NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (int j = 0; j < bytes.length; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static void main(String[] args){
        Date startDate = new Date();
        Date markTime = new Date(startDate.getYear(),startDate.getMonth(),startDate.getDate(),Integer.parseInt("8"),0,0);
       List<Integer>     listI=new ArrayList<Integer>();
        List<String>     list=new ArrayList<String>();
       listI.add(4159408);
        list.add("高血压");
//        list.add("颅内肿瘤");
//        list.add("咯血");
//        list.add("流鼻血");
//        list.add("流鼻涕");
//        list.add("泪道堵塞");
//        list.add("泪道病");
//        list.add("肋间神经痛");
//        list.add("老人家腿麻");
//        list.add("老人肚子疼有个包");
//        list.add("老年性痴呆");
//        list.add("老年肾损害");
//        list.add("老年人牙疼");
//        list.add("老年人消化不好");
//        list.add("老年人下颌长肿物");
//        list.add("老年人睡觉磨牙");
//        list.add("克罗恩氏病");
//       list.add("Henoch-Schonlein紫癜");
        for(int i=0;i<list.size();i++) {
            String mdString = MD5Util.encode(list.get(i) + "2002-1-1 17:55:00" + "亚里士多德");
            System.out.printf(list.get(i)+"---------"+mdString);
        }
        for(int i=0;i<listI.size();i++) {
            String mdString = MD5Util.encode(listI.get(i) + "2002-1-1 17:55:00" + "亚里士多德");
            System.out.printf("---------"+listI.get(i)+"---------"+mdString);
        }
        //System.out.printf(markTime.toString());
//        System.out.printf(mdString);
//        System.out.printf(mdString2);


    }

}
