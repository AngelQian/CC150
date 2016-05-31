import java.util.Arrays;

public class Chapter1 {

    public static void main(String[] args) {
        System.out.println(rotateString("waterbottle","erbottlewat"));
//        System.out.println(stringCompression("hello"));
//        String[][] strArr = new String[][]{{"1","2","3"},{"6","5","4"},{"7","8","9"}};
//        String[][] rt = rotateImage2(strArr);
//        for(int i=0; i<3; i++){
//            for(int j=0; j<3; j++) {
//                System.out.print(rt[i][j]);
//            }
//            System.out.println();
//        }
    }

    public static boolean UniqueCharacterInString(String str){
        int checker = 0;
        for(int i=0; i<str.length(); i++){
            int ch = str.charAt(i)-'a';
            if((checker&(1<<ch))>0)
                return false;
            checker |= (1<<ch);
        }
        return true;
    }

    public static boolean isPermutation (String s1, String s2){
        // hashmap: key-alphabet, value-count
        // sort, compare
        // character array
        if (s1.length()!=s2.length())
            return false;

        int[] letters = new int[128]; //ASCII 128个
        char[] s1arr = s1.toCharArray();
        for(char c: s1arr){
            letters[c]++;
        }

        char[] s2arr = s2.toCharArray();
        for(char c: s2arr){
            if(--letters[c]<0)
                return false;
        }
        return true;
    }

    public static String replaceSpace (char[] arr, int len, String str){
        // stringbuffer append
        // first scan to get the space counts, second scan to update the space reversely, in place
        StringBuffer buffer = new StringBuffer();
        for(char ch : arr){
            if(ch==' ')
                buffer.append(str);
            else
                buffer.append(ch);
        }
        return buffer.toString();
    }

    public static String stringCompression (String str){
        // sort
        StringBuffer buffer = new StringBuffer();
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        for(int i=0; i<arr.length;){
            int j=i+1, count=1;
            while(j<arr.length && arr[j]==arr[i]){
                count ++;
                j++;
            }
            buffer.append(arr[i]);
            if(count>1)
                buffer.append(count);
            i=j;
        }
        return buffer.toString().length()>str.length()?str:buffer.toString();
    }

    public static String[][] rotateImage1(String[][] images){
        // diagonal
        int row=images.length;
        if(row<1)
            return images;
        int col=images[0].length;

        String[][] rotate = new String[col][row];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                rotate[j][row-i-1] = images[i][j];
            }
        }
        return rotate;
    }

    public static String[][] rotateImage2(String[][] images){
        // diagonal
        int row=images.length;
        if(row<1)
            return images;
        int col=row;

        for(int i=0; i<row; i++){
            for(int j=0; j<row-i; j++){
                String temp = images[i][j];
                images[i][j] = images[row-1-i][j];
                images[row-1-i][j] = images[row-1-i][col-1-j];
                images[row-1-i][col-1-j] = images[i][col-j-1];
                images[i][col-j-1] = temp;
            }
        }
        return images;
    }

    public static int[][] updateImageZero(int[][] images){
        // record the horizantal and vertical index
        int row=images.length;
        if(row<1)
            return images;
        int col=row;

        boolean[] harr = new boolean[row];
        boolean[] varr = new boolean[col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(images[i][j]==0){
                    harr[i]=true;
                    varr[j]=true;
                }
            }
        }

        for(int i=0; i<row; i++){
            if(harr[i]==true)
            {
                for(int j=0; j<col; j++)
                    images[i][j]=0;
            }
        }

        for(int j=0; j<col; j++){
            if(varr[j]==true)
            {
                for(int i=0; i<row; i++)
                    images[i][j]=0;
            }
        }
        return images;
    }

    public static boolean rotateString(String str1, String str2){
        // reverse trick
        return (str1+str1).indexOf(str2)>=0;
    }
}
