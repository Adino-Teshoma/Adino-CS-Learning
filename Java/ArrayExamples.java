import java.util.Scanner;
class ArrayExample{
public static void main(String []args){
/*int b[] ={34,23,11,67,23};
//System.out.println();("Array Elements");
for(int i = 0;i<b.length;i++){
System.out.println("b[" + i + "] = " + b[i]); 
}*/
/*Scanner scanner = new Scanner(System.in); 
int[] numbers = new int[5];
System.out.println("Enter 5 integers:"); 
for (int i = 0; i < numbers.length; i++)
 { 
numbers[i] = scanner.nextInt();
 } 
System.out.println("You entered:"); 
for (int i = 0; i < numbers.length; i++)
 { 
System.out.println("numbers[" + i + "] = " + numbers[i]);
*/
int a[][] = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
 }


}

}
