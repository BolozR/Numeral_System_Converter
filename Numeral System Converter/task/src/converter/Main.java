package converter;

import java.util.Scanner;

public class Main {

    static void doubl(int source, String in, int radix){
        Double resFrac = 0.0, tempToRad, resToRad = 0.0, minus;
        String[] strToDec = in.split("[.]")[1].split("");
        char[][] toRad = new char[5][2];
        for (int i = 0; i < strToDec.length; i++){
            if(strToDec[i].matches("[\\D]")) minus = 87.0;
            else minus = 48.0;
            resFrac += (strToDec[i].toLowerCase().codePointAt(0) - minus) / Math.pow(source, i+1);
        }
        tempToRad = resFrac;
        for (int i = 0; i < 5; i++){
            tempToRad = tempToRad*radix;
            if(tempToRad.toString().split("[.]")[0].length() >= 2)
                toRad[i][0] = (char) (Integer.parseInt(tempToRad.toString().split("[.]")[0]) + 87);
            else toRad[i][0] = tempToRad.toString().split("[.]")[0].charAt(0);
            tempToRad = Double.parseDouble("0."+tempToRad.toString().split("[.]")[1]);
        }
        System.out.print( integer(source, in.split("[.]")[0], radix)+".");
        for (int i = 0; i < 5; i++) System.out.print(toRad[i][0]);
    }

    static String integer(int source, String in, int radix){
        int lon;
        String num = "";
        if (source == 1){
            lon = Integer.parseInt(in);
            int i = 0;
            for(;lon%10 == 1; i++){
                lon=lon/10;
            };
            lon = i;
        } else lon = Integer.parseInt(in, source);
        if(radix == 1){
            for (int i = 0; i < lon;i++, num+="1");
        }
        else num = Integer.toString(lon, radix);
        return num;
    }

    static void output(int radix, String num){
        switch (radix){
            case 8:
                System.out.println("0" + num);
                break;
            case 2:
                System.out.println("0b" + num);
                break;
            case 16:
                System.out.println("0x" + num);
                break;
            default:
                System.out.println(num);
                break;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int source = Integer.parseInt(scanner.nextLine());
            String lon = scanner.nextLine();
            int radix = Integer.parseInt(scanner.nextLine());
            if(radix <= 0 || radix > 36) throw new Exception();
            if (lon.indexOf('.') == -1) System.out.println(integer(source, lon, radix));
            else doubl(source, lon, radix);
        } catch (Exception e){
            System.out.println("error");
        }
    }
}