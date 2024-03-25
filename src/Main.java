
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String[] parsedLine = line.split(" = ");
        int n = Integer.parseInt(parsedLine[1]);
        String symbolsLine = "+" + parsedLine[0];
        symbolsLine = symbolsLine.replace("?", "");
        symbolsLine = symbolsLine.replace(" ", "");
        MinMaxSymbols m = countMathSymbols(line);
        int minSymbols = m.minuses;
        int maxSymbols = m.pluses+1;
        int min = maxSymbols - n*minSymbols;
        int max = n*maxSymbols - minSymbols;

        if (min <= n && n <= max) {
            int belowMax = max-n;
            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < symbolsLine.length(); i++) {
                int delta = Math.min(n-1, belowMax);
                belowMax -= delta;
                if (symbolsLine.charAt(i) == '+') {
                    if(i != 0) ans.append(" + ");
                    ans.append(n-delta);
                } else {
                    ans.append(" - ").append(1+delta);
                }
            }
            ans.append(" = ").append(n);
            System.out.println("Possible");
            System.out.println(ans);
        } else {
            System.out.println("Impossible");
        }
    }

    static class MinMaxSymbols {
        public int pluses;
        public int minuses;

        public MinMaxSymbols(int pluses, int minuses) {
            this.pluses = pluses;
            this.minuses = minuses;
        }
    }

    private static MinMaxSymbols countMathSymbols(String line){
        int minuses = 0;
        int pluses = 0;
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if(symbol == '-') minuses++;
            else if(symbol == '+') pluses++;
        }
        return new MinMaxSymbols(pluses, minuses);
    }
}
