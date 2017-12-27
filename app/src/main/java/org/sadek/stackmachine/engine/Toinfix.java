package org.sadek.stackmachine.engine;

/**
 * Created by Ahmed on 11-Dec-17.
 */

import java.util.Stack;

public class Toinfix {

    boolean IsOperand(char C)
    {
        if(C >= '0' && C <= '9') return true;
        if(C >= 'a' && C <= 'z') return true;
        if(C >= 'A' && C <= 'Z') return true;
        return false;
    }
    boolean IsOperator(char C)
    {
        if(C == '+' || C == '-' || C == '*' || C == '/' || C== '$')
            return true;

        return false;
    }
    int IsRightAssociative(char op)
    {
        if(op == '$') return 1;
        return 0;
    }
    int GetOperatorWeight(char op)
    {
        int weight = -1;
        switch(op)
        {
            case '+':
            case '-':
                weight = 1;
                break;
            case '*':
            case '/':
                weight = 2;
                break;
            case '$':
                weight = 3;
                break;
        }
        return weight;
    }
    boolean HasHigherPrecedence(char op1, char op2)
    {
        int op1Weight = GetOperatorWeight(op1);
        int op2Weight = GetOperatorWeight(op2);


        if(op1Weight == op2Weight)
        {
            if(IsRightAssociative(op1) == 0) return false;
            else return true;
        }
        if(op1Weight > op2Weight)   return true;
        else return  false;
    }
    String InfixToPostfix(String exp)
    {

        Stack<Character> S = new Stack<Character>();
        char[] expression = exp.toCharArray();
        String postfix = "";
        for(int i = 1; i < expression.length; i++) {


            if(expression[i] == ' ' || expression[i] == ',') continue;

            else if(IsOperator(expression[i]))
            {
                while( (!S.empty()) && (S.peek() != '(' ) && ( HasHigherPrecedence(S.peek(),expression[i]) ) )
                {
                    postfix+= S.peek();
                    S.pop();
                }
                S.push(expression[i]);
            }
            // Else if character is an operand
            else if(IsOperand(expression[i]))
            {
                postfix +=expression[i];
            }

            else if (expression[i] == '(')
            {
                S.push(expression[i]);
            }

            else if(expression[i] == ')')
            {
                while(!S.empty() && S.peek() !=  '(') {
                    postfix += S.peek();
                    S.pop();
                }
                S.pop();
            }
        }

        while(!S.empty()) {
            postfix += S.peek();
            S.pop();
        }

        return postfix;
    }


}