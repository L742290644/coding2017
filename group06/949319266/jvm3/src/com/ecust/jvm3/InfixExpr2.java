package com.ecust.jvm3;

import java.util.HashMap;
import java.util.Stack;



public class InfixExpr2 {



	public static enum TypeToken {



		add('+',0),substruct('-',1),devide('/',3),plus('*',2);



		TypeToken(char token, Integer priority) {

			this.token = token;

			this.priority = priority;

		}



		private char token;



		private Integer priority;

	}



	String expr = null;

	

	public InfixExpr2(String expr) {

		this.expr = expr;

	}

//3*20+12*5-40/2

	public Double evaluate() {

		HashMap<String, Integer> map = new HashMap<>();

		map.put("+",0);

		map.put("-",1);

		map.put("/",3);

		map.put("*",2);





		Stack tokenStack = new Stack();

		Stack numStack = new Stack();

		char[] chars = expr.toCharArray();

		boolean isNem = true;

		for (int i = 0; i < expr.length(); i++) {

			String token = (expr.charAt(i)) + "";

			Integer priprity = map.get(token);

			if (priprity != null) {

				//表示是运算符

				if (!tokenStack.isEmpty() && priprity < map.get(tokenStack.peek())) {

					Float num2 = Float.valueOf(numStack.pop().toString());

					Float num1 = Float.valueOf(numStack.pop().toString());

					String pop = tokenStack.pop()+"";

					if (pop.equals("-")) {

						numStack.push(num1 - num2);

					} else if (pop.equals("*")){

						numStack.push(num1 * num2);

					} else if (pop.equals("/")){

						numStack.push(num1 / num2);

					} else {

						throw new RuntimeException();

					}



				}

				tokenStack.push(token);

				isNem = true;

			} else if(token.matches("\\d{1}")) {

				//表示是数字

				if (isNem) {

					numStack.push(token);

				} else {

					numStack.push(numStack.pop().toString() + token);

				}

				isNem = false;

			} else {

				throw new RuntimeException();

			}

		}

		while (!tokenStack.isEmpty()) {

			System.out.println(tokenStack.size());

			if (tokenStack.peek().equals("+")) {

				Float num2 = Float.valueOf(numStack.pop().toString());

				Float num1 = Float.valueOf(numStack.pop().toString());

				numStack.push(num1+num2+"");

			} else if (tokenStack.peek().equals("-")) {

				Float num2 = Float.valueOf(numStack.pop().toString());

				Float num1 = Float.valueOf(numStack.pop().toString());

				numStack.push(num1-num2+"");

			} else if (tokenStack.peek().equals("/")) {

				Float num2 = Float.valueOf(numStack.pop().toString());

				Float num1 = Float.valueOf(numStack.pop().toString());

				numStack.push(num1/num2+"");

			} else if (tokenStack.peek().equals("*")) {

				Float num2 = Float.valueOf(numStack.pop().toString());

				Float num1 = Float.valueOf(numStack.pop().toString());

				numStack.push(num1*num2+"");

			}  else {

				throw new RuntimeException();

			}

			tokenStack.pop();

		}

		return Double.valueOf(numStack.pop().toString());

	}

}
