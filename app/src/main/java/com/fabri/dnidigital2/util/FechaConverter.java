// Definimos el paquete donde se encuentra la clase
package com.fabri.dnidigital2.util;

// Importamos las clases necesarias para manipular fechas y excepciones
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Clase encargada de convertir fechas a diferentes formatos
public class FechaConverter {

    // Definimos constantes con distintos formatos de fecha
    public static final String SQLITE_DATE = "yyyy-MM-dd"; // Formato de fecha en SQLite
    public static final String SQLITE_DATETIME = "yyyy-MM-dd HH:mm:ss"; // Fecha y hora en SQLite
    public static final String MYSQL = "yyyy/MM/dd HH:mm:ss"; // Formato de fecha y hora en MySQL
    public static final String MYSQL_DATE = "yyyy/MM/dd"; // Solo fecha en MySQL
    public static final String NODEJS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; // Formato de fecha de Node.js
    public static final String FORMAT_DNI = "dd/MM/yyyy"; // Formato utilizado en documentos de identidad

    // Metodo para convertir una fecha (Date) a String usando el formato por defecto (SQLITE_DATE)
    public static String toString(Date date) {
        if (date == null) return null; // Si la fecha es nula, retornamos null
        return new SimpleDateFormat(SQLITE_DATE, Locale.getDefault()).format(date); // Convertimos la fecha a String
    }

    // Metodo para convertir una fecha a String con un formato específico
    public static String toString(Date date, String formato) {
        if (date == null) return null; // Si la fecha es nula, retornamos null
        return new SimpleDateFormat(formato, Locale.getDefault()).format(date); // Convertimos la fecha al formato especificado
    }

    // Metodo para convertir una fecha a String con un formato y un locale específico
    public static String toString(Date date, String formato, Locale locale) {
        if (date == null) return null; // Si la fecha es nula, retornamos null
        return new SimpleDateFormat(formato, locale).format(date); // Convertimos la fecha al formato y locale especificado
    }

    // Metodo para convertir un String en formato "yyyy-MM-dd HH:mm:ss" a un objeto Date
    public static Date toDate(String date) {
        if (date == null || date.isEmpty()) return null; // Si el String está vacío o es nulo, retornamos null

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(date); // Intentamos parsear la fecha
        } catch (ParseException e) {
            return null; // Si hay un error en el formato, retornamos null
        }
    }

    // Metodo para convertir un String con un formato específico en un objeto Date
    public static Date toDate(String date, String formato) {
        if (date == null || date.isEmpty()) return null; // Si el String está vacío o es nulo, retornamos null

        try {
            return new SimpleDateFormat(formato, Locale.getDefault()).parse(date); // Intentamos parsear la fecha con el formato dado
        } catch (ParseException e) {
            return null; // Si hay un error en el formato, retornamos null
        }
    }

    // Metodo para convertir un String con un formato y un locale específico en un objeto Date
    public static Date toDate(String date, String formato, Locale locale) {
        if (date == null || date.isEmpty()) return null; // Si el String está vacío o es nulo, retornamos null

        try {
            return new SimpleDateFormat(formato, locale).parse(date); // Intentamos parsear la fecha con el formato y locale dados
        } catch (ParseException e) {
            return null; // Si hay un error en el formato, retornamos null
        }
    }
}
