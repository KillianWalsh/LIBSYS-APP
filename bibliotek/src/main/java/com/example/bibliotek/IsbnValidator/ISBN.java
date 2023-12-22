package com.example.bibliotek.IsbnValidator;

//public class ISBN {

//    public static boolean isISBNValid(String isbn){

//  1234567890 = fail
//  1-234-36789-0
//  1-234-56489-0
//  1-234-56789-X
//  1-83456-789-0


//        använder inte
//        int correctLengthOfNumber = isbn.length();
//        if (correctLengthOfNumber < 10){
//            return false;
//        }
//        int sum = 0;
//        for (int i = 0; i < 9; i++) {
//            int digit = isbn.charAt(i) - '0';
//            if (0 > digit || 9 < digit) {
//                return false;
//            }
//            sum += (digit * (10 - i));
//        }
//            char lastIsbn = isbn.charAt(9);
//            if (lastIsbn != 'X' && (lastIsbn < '0' || lastIsbn > '9')) {
//                return false;
//            }
//
//            sum += ((lastIsbn == 'X') ? 10 : (lastIsbn - '0'));
//
//        System.out.println(sum);
//        System.out.println(sum % 11);
//
//        return (sum % 11 == 0);
//    }
//        if (isbn.length() != 13) {
//            System.out.println("ISBN är ogiltigt");
//            return false;
//        }
//
//
//        if (isAValidISBN(Long.parseLong(isbn))) {
//            System.out.println(isbn + " giltigt ISBN");
//            return true;
//        } else {
//            System.out.println(isbn + " ogiltigt ISBN");
//            return false;
//        }
//    }
//
//    private static int getSum(long isbn) {
//        int count = 0;
//        int sum = 0;
//        do {
//            sum += count % 2 == 0 ? isbn % 10 : 3 * (isbn % 10);
//            count++;
//            isbn /= 10;
//        } while (isbn > 0);
//        return sum;
//    }
//
//    private static boolean isAValidISBN(long isbn) {
//        return getSum(isbn) % 10 == 0;
//    }
//}
