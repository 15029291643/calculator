package com.example.calculator.model

import java.util.*


object Calculator {
    // 计算
    @Throws(Exception::class)
    fun count(s: String) = countInverse(toInverse(toInfix(s))).toString()

    // 计算逆波兰表达式
    @Throws(Exception::class)
    private fun countInverse(list: List<String>): Double {
        val numbers = Stack<Double>()
        for (e in list) {
            if (isOperator(e)) {
                numbers.push(operation(numbers.pop(), numbers.pop(), e[0]))
            } else {
                numbers.push(java.lang.Double.valueOf(e))
            }
        }
        return numbers.pop()
    }

    // 中缀表达式转逆波兰表达式
    @Throws(Exception::class)
    private fun toInverse(list: List<String>): List<String> {
        val numbers = Stack<String>()
        val operators = Stack<Char>()
        for (e in list) {
            if (isOperator(e)) {
                val c = e[0]
                if (c == '(') {
                    operators.push(c)
                } else if (c == ')') {
                    while (operators.peek() != '(') {
                        numbers.push(operators.pop().toString())
                    }
                    operators.pop()
                } else if (operators.empty() || operators.peek() == '(' || isPrecedence(c,
                        operators.peek())
                ) {
                    operators.push(c)
                } else {
                    while (!operators.empty() && (operators.peek() != '(' || !isPrecedence(c,
                            operators.peek()))
                    ) {
                        numbers.push(operators.pop().toString())
                    }
                    operators.push(c)
                }
            } else {
                numbers.push(e)
            }
        }
        while (!operators.empty()) {
            numbers.push(operators.pop().toString())
        }
        return numbers
    }

    // 字符串转中缀表达式
    @Throws(Exception::class)
    private fun toInfix(s: String): List<String> {
        val builder = StringBuilder()
        val list: MutableList<String> = ArrayList()
        for (c in s.toCharArray()) {
            if (isNumber(c)) {
                builder.append(c)
            } else if (isOperator(c)) {
                if (builder.isNotEmpty()) {
                    list.add(builder.toString())
                    builder.setLength(0)
                }
                list.add(c.toString())
            } else {
                throw Exception("Invalid character: $c")
            }
        }
        if (builder.isNotEmpty()) {
            list.add(builder.toString())
            builder.setLength(0)
        }
        return list
    }

    // 判断运算符优先级
    @Throws(Exception::class)
    private fun isPrecedence(c1: Char, c2: Char) = priority(c1) > priority(c2)

    // 计算
    @Throws(Exception::class)
    private fun operation(d2: Double, d1: Double, c: Char) = when (c) {
        '+' -> d1 + d2
        '-' -> d1 - d2
        '*' -> d1 * d2
        '/' -> d1 / d2
        else -> throw Exception("Invalid operation: $c")
    }

    // 运算符优先级
    @Throws(Exception::class)
    private fun priority(c: Char) = when (c) {
        '+', '-' -> 1
        '*', '/' -> 2
        '(', ')' -> 3
        else -> throw Exception("Illegal character: $c")
    }

    // 判断数字
    private fun isNumber(c: Char): Boolean {
        return c in '0'..'9' || c == '.'
    }

    // 判断运算符
    private fun isOperator(c: Char) =
        c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'

    // 判断运算符
    private fun isOperator(s: String) = s.length == 1 && isOperator(s[0])
}