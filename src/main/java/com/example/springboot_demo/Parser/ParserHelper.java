package com.example.springboot_demo.Parser;

public class ParserHelper {
    public static String generateKhaiTamProductCode(String name) {
        String input = name.toUpperCase();
////        Pattern pattern = Pattern.compile("[0-9]{4}([A-Z]+)?(-[A-Z0-9]+(/[A-Z0-9]+)?)?");
//        Pattern pattern = Pattern.compile("\\s[0-9]{4}");
//        Matcher matcher = pattern.matcher(input);
//        if (matcher.find()) {
//            return "KT" + input.substring(matcher.start() + 1, input.indexOf("(")).replaceAll("-", " ");
//        }
        String tmp = input.substring(22, input.indexOf("/", 23));
        return "KT" + tmp;
    }

    public static String generateVanLangProductCode(String name) {
        String input = name.toUpperCase();
////        Pattern pattern = Pattern.compile("[0-9]{4}([A-Z]+)?(-[A-Z0-9]+(/[A-Z0-9]+)?)?");
//        Pattern pattern = Pattern.compile("\\s[0-9]{4}");
//        Matcher matcher = pattern.matcher(input);
//        if (matcher.find()) {
//            return "KT" + input.substring(matcher.start() + 1, input.indexOf("(")).replaceAll("-", " ");
//        }
        String tmp = input.substring(22, input.indexOf("/", 23));
        return "VL" + tmp;
    }

    public static String capitailizeWord(String str) {
        StringBuffer s = new StringBuffer();

        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {


            if (ch == ' ' && str.charAt(i) != ' ')
                s.append(Character.toUpperCase(str.charAt(i)));
            else if (ch != ' ' && str.charAt(i) != ' ') s.append(Character.toLowerCase(str.charAt(i)));
            else s.append(str.charAt(i));
            ch = str.charAt(i);
        }
        return s.toString().trim();
    }

//    public static void main(String[] args) {
////        SpringApplication.run(SpringbootDemoApplication.class, args)
////        String s = capitailizeWord()
//        System.out.println(ParserHelper.capitailizeWord("người,!! THôNGmINh Lắm ĐẤy cÓ bIẾT"));
//
//    }


}
