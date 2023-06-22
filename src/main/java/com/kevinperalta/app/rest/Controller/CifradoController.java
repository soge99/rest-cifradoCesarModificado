package com.kevinperalta.app.rest.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
@RestController
public class CifradoController {
    //metodo get que recibe 2 parametros por la url. string cadena = texto a cifrar. string clave = clave con la que se cifra el texto
    @GetMapping(value = "/encriptar")
    public String encriptar(@RequestParam(name = "cadena") String cadena, @RequestParam(name = "clave") String clave){

        //Declaracion de variables

        //cadena = texto a cifrar. clave = clave numerica con la que se cifra la variable cadena

        //lista de valores ascci equivalentes a los caracteres del texto a ser cifrado
        List<Integer> ascciList = new ArrayList<>();

        //String que almacena la cadena cifrada
        StringBuilder cadenaCifrada= new StringBuilder();

        int cont =0;
        int tempAscciCharacter;
        int tempAscciCifrado;


        //controla la respuesta si N y T esten dentro de los rangos
        if(clave.length() >9 || clave.length() < 1){
            return("La clave debe contener al menos 1 carácter y máximo 9 ");
        } else if (cadena.length() >10000 || cadena.length() < 1) {
            return("El texto a cifrar debe contener al menos un carácter y máximo 10.000 caracteres ");
        }

        //quita espacios al inicio y al final de la cadena a cifrar y la transforma. Además transforma la cadena a minusculas para utilizar el codigo ascci
        cadena = cadena.replaceAll(" +", "").toLowerCase();

        //Crea una lista con los codigos ascci de todos los caracteres de la cadena a cifrar
        for(int i =0; i < cadena.length();i++){
            tempAscciCharacter = cadena.charAt(i);
            ascciList.add(tempAscciCharacter);
        }

        //cifra la cadena si es que se cumple con el caracter numerico de la clave de cifrado
        for(Integer asciiElem: ascciList){

            if(cont == clave.length()){
                cont = 0;
            }
            try{
                tempAscciCifrado = asciiElem + Integer.parseInt(String.valueOf(clave.charAt(cont)));

            }catch(Exception e){
                return("La clave debe ser numérica. " + e);
            }

            if(tempAscciCifrado > 122){
                tempAscciCifrado = 96 + (tempAscciCifrado - 122) ;
            }

            cadenaCifrada.append((char)(tempAscciCifrado));
            cont = cont + 1;
        }
        return(cadenaCifrada.toString().toUpperCase());

    }

}



