package com.teachmeskills.lesson14.task1.run;

import com.teachmeskills.lesson14.task1.service.DocReader;

public class Runner {
    public static void main(String[] args) {
        try {
            DocReader.readDocNames();
        } catch (Exception e) {
            System.out.println("Unexpected error. Please try later or contact the administrator");
        }
    }
}
