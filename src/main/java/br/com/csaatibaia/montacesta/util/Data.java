package br.com.csaatibaia.montacesta.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {
    
    public static String pegarData(){
        
        LocalDate localDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return localDate.format(formatter);
    }
}
