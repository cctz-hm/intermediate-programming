public class MethodsPractice {
     public static void main(String[] args) throws Exception {
         //call all functions here 
         System.out.println("Positive? " + isPositive(10));
         System.out.println("least " + findLeast(3, 4, 5));
         System.out.println("triangle area " + triangle(5));
         System.out.println("last digit " + shareLastDigit(193, 183));
         System.out.println("sum of digits " + sumDigits(123));
         System.out.println("to decimal " + toDecimal(101));

     }
 //make functions
 static boolean isPositive(int input_number){
     return input_number>0;

 } 

 static int findLeast(int a, int b, int c){
    if (a < b && a < c){
        return a;
    } else if (b < a && b < c){
        return b;
    } else {
        return c;
    }
    }

static double triangle(int a){
    double area = (Math.sqrt(3) / 4) * Math.pow(a, 2);
    return area;
}

static boolean shareLastDigit(int a, int b){
    return (a % 10 == b % 10);
}

static int sumDigits(int input){
    int sum = 0;
    while (input > 0){
        sum += input % 10;
        input /= 10;
    }
    return sum;

}

static int toDecimal(int binary){
    int decimal = 0;
    int power = 0;

    while (binary > 0){
        int lastDigit = binary % 10;
        decimal += lastDigit * Math.pow(2, power);
        binary /= 10;
        power++;
    }

    return decimal;
    

}

 }

 
     