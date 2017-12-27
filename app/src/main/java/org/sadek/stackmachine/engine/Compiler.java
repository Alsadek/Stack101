package org.sadek.stackmachine.engine;

import static android.text.TextUtils.substring;

public class Compiler {
    public Compiler() {
    }

    public Result compile(String expr) {

        Result result = new Result();

        Toinfix e = new Toinfix();
        String ex = e.InfixToPostfix(expr);

        String [] prog = new String[ex.length()+1];
        String [] src = new String[]{"push A", "push B", "push C", "push D"};
        String [] sources = new String[4];

        int x = 0;
        for(int i = 0; i < ex.length(); i++){
            char c = ex.charAt(i);
            if( e.IsOperand(c) )  {
                prog[i] = src[x];
                sources[x] = src[x].substring(src[x].length() - 1);
                x++;
            }
            else if ( c == '+' ){
                prog[i] = "add";
            }
            else if ( c == '-' ){
                prog[i] = "subt";
            }
            else if ( c == '*' ){
                prog[i] = "mult";
            }
            else if ( c == '/' ){
                prog[i] = "div";
            }
        }
        prog[ex.length()] = "pop X";



        result.expr = expr;
        result.postfix = ex;
        result.program = prog;
        result.sources = sources;
        result.destination = "X";
        result.error = null;



        return result;
    }

    public class Result {
        private String expr = null;
        private String postfix = null;
        private String[] program = null;
        private String sources[] = null;
        private String destination = null;
        private String error = null;

        public boolean isSuccessful() {
            if (error==null) return true;
            else return false;
        }
        public String getExpr() {
            return expr;
        }
        public String getPostfix() {
            return postfix;
        }
        public String[] getProgram() {
            return program;
        }
        public String[] getSources() {
            return sources;
        }
        public String getDestination() {
            return destination;
        }
        public String getError() {
            return error;
        }
    }
}