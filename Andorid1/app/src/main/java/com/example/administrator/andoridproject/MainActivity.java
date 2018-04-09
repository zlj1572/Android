package com.example.administrator.andoridproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Button[] btn = new Button[10];  //0~9十个按键

    private TextView input;                 //显示器,用于显示输入字符串和输出结果

    private TextView memory;                   //显示器下方的记忆器，用于记录上一次计算结果


    private Button      //各个按钮
            div, mul, sub, add, equal, sin, cos, tan, lg, ln, bksp, mi,
            left, right, dot, c,buttonHex,buttonUnit;

    private static final String TAG = "myTag";


    public String str_old;              //保存原来的输入形式

    public boolean inputControl = true;       //输入控制，true为重新输入，false为接着输入

    public double pi=4*Math.atan(1);    //π值：3.14

    public boolean inputLock = true;     //输入锁：true表示正确，可以继续输入；false表示有误，输入被锁定

    public boolean equalsFlag = true;  //判断是否是按=之后的输入，true表示输入在=之前，false反之

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取界面元素
        input = (TextView)findViewById(R.id.input);
        memory = (TextView)findViewById(R.id.MemInformation);

        //0-9十个数字
        btn[0] = (Button)findViewById(R.id.zero);
        btn[1] = (Button)findViewById(R.id.one);
        btn[2] = (Button)findViewById(R.id.two);
        btn[3] = (Button)findViewById(R.id.three);
        btn[4] = (Button)findViewById(R.id.four);
        btn[5] = (Button)findViewById(R.id.five);
        btn[6] = (Button)findViewById(R.id.six);
        btn[7] = (Button)findViewById(R.id.seven);
        btn[8] = (Button)findViewById(R.id.eight);
        btn[9] = (Button)findViewById(R.id.nine);

        //加减乘除等号
        div = (Button)findViewById(R.id.buttonDivide);
        mul = (Button)findViewById(R.id.buttonTimes);
        sub = (Button)findViewById(R.id.sub);
        add = (Button)findViewById(R.id.add);
        equal = (Button)findViewById(R.id.buttonIs);

        //三角函数
        sin = (Button)findViewById(R.id.sin);
        cos = (Button)findViewById(R.id.cos);
        tan = (Button)findViewById(R.id.tan);

        //对数
        lg = (Button)findViewById(R.id.lg);
        ln = (Button)findViewById(R.id.ln);

        mi = (Button)findViewById(R.id.buttonN);        //幂运算
        bksp = (Button)findViewById(R.id.buttonDel);    //退格

        //左右括号
        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);

        dot = (Button)findViewById(R.id.buttonPoint);   //小数点
        c = (Button)findViewById(R.id.buttonCE);        //clear

        buttonHex = (Button)findViewById(R.id.buttonHex);   //进制转换
        buttonUnit = (Button)findViewById(R.id.buttonUnit); //单位转换

        //为数字按键绑定监听器
        for(int i = 0; i < 10; ++i) {
            btn[i].setOnClickListener(actionPerformed);
        }

        //为加减乘除等按键绑定监听器
        div.setOnClickListener(actionPerformed);
        mul.setOnClickListener(actionPerformed);
        sub.setOnClickListener(actionPerformed);
        add.setOnClickListener(actionPerformed);
        equal.setOnClickListener(actionPerformed);
        sin.setOnClickListener(actionPerformed);
        cos.setOnClickListener(actionPerformed);
        tan.setOnClickListener(actionPerformed);
        lg.setOnClickListener(actionPerformed);
        ln.setOnClickListener(actionPerformed);
        mi.setOnClickListener(actionPerformed);
        bksp.setOnClickListener(actionPerformed);
        left.setOnClickListener(actionPerformed);
        right.setOnClickListener(actionPerformed);
        dot.setOnClickListener(actionPerformed);
        c.setOnClickListener(actionPerformed);

        //进制转换的按钮监听，点击创建新的Activity，具体显示什么数据用Intent传过去
        buttonHex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HexActivity.class);
                startActivity(intent);
            }
        });

        //单位换算，采用Dialog先选择哪种单位，然后点击创建新的Activity，具体显示什么数据用Intent传过去
        buttonUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"长度","质量","面积"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("单位换算")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MainActivity.this, UnitActivity.class);
                                switch (i){
                                    case 0:
                                        intent.putExtra("text1", "千米");
                                        intent.putExtra("text2", "米");
                                        intent.putExtra("text3", "厘米");
                                        intent.putExtra("text4", "毫米");
                                        intent.putExtra("dw", 1000);
                                        intent.putExtra("dw1", 100);
                                        intent.putExtra("dw2", 10);

                                        break;
                                    case 1:
                                        intent.putExtra("text1", "吨");
                                        intent.putExtra("text2", "千克");
                                        intent.putExtra("text3", "克");
                                        intent.putExtra("text4", "毫克");
                                        intent.putExtra("dw", 1000);
                                        intent.putExtra("dw1", 1000);
                                        intent.putExtra("dw2", 1000);
                                        break;
                                    case 2:
                                        intent.putExtra("text1", "平方米");
                                        intent.putExtra("text2", "平方分米");
                                        intent.putExtra("text3", "平方厘米");
                                        intent.putExtra("text4", "平方毫米");
                                        intent.putExtra("dw", 100);
                                        intent.putExtra("dw1", 1000);
                                        intent.putExtra("dw2", 1000);
                                        break;
                                }
                                startActivity(intent);
                            }
                        });
                builder.show();
            }
        });
    }

    /**
     * 捕捉键盘命令
     */
    String[] Tipcommand = new String[500];     //命令缓存，用于检测输入合法性
    int tip_i = 0;      //Tipcommand的指针
    private View.OnClickListener actionPerformed = new View.OnClickListener() {
        public void onClick(View v) {

            //获取按键上的命令(command表示当前button上的字符串)
            String command = ((Button)v).getText().toString();

            //显示器上的字符串
            String str = input.getText().toString();

            /*检测输入是否合法*/
            if(!equalsFlag && "0123456789.()sincostanlnlg+-*/^".contains(command)) {
                //检测显示器上的字符串是否合法
                if(isRight(str)) {
                    if("+-*/^)".contains(command)) {
                        for(int i=0;i<str.length();i++) {
                            Tipcommand[tip_i] = String.valueOf(str.charAt(i));
                            tip_i++;
                        }
                        inputControl = false; //接着输入
                    }
                }
                else {
                    input.setText("");
                    inputControl = true;      //重新输入
                    tip_i = 0;
                    inputLock = true;
                }
                equalsFlag = true;     //输入等号之前
            }


            if("0123456789.()sincostanlnlg+-*/^".contains(command) && inputLock) {
                Tipcommand[tip_i] = command;
                tip_i++;
            }

            //若输入正确，则将输入信息显示到显示器上
            if("0123456789.()sincostanlnlg+-*/^".contains(command) && inputLock) {
                print(command);
            }

            /*退格键的使用*/
            else if(command.compareTo("DEL") == 0){

                //刚开始就点退格，则无效
                if(str.length() == 0){
                    input.setText("");
                    inputControl = true;
                    tip_i = 0;
                }
                else{
                    //如果在按等号之前输入退格键
                    if(equalsFlag) {
                        //一次删除3个字符
                        if(delete(str) == 3) {
                            if(str.length() > 3)
                                input.setText(str.substring(0, str.length() - 3));
                            else if(str.length() == 3) {
                                input.setText("");
                                inputControl = true;
                                tip_i = 0;
                            }
                        }
                        //一次删除2个字符
                        else if(delete(str) == 2) {
                            if(str.length() > 2)
                                input.setText(str.substring(0, str.length() - 2));
                            else if(str.length() == 2) {
                                input.setText("");
                                inputControl = true;
                                tip_i = 0;
                            }
                        }
                        //一次删除一个字符
                        else if(delete(str) == 1) {
                            //若之前输入的字符串合法则删除一个字符
                            if(isRight(str)) {
                                if(str.length() > 1)
                                    input.setText(str.substring(0, str.length() - 1));
                                else if(str.length() == 1) {
                                    input.setText("");
                                    inputControl = true;
                                    tip_i = 0;
                                }
                                //若之前输入的字符串不合法则删除全部字符
                                else {
                                    input.setText("");
                                    inputControl = true;
                                    tip_i = 0;
                                }
                            }
                        }

                        if(input.getText().toString().compareTo("-") == 0 || !equalsFlag) {
                            input.setText("");
                            inputControl = true;
                            tip_i = 0;
                        }
                        inputLock = true;
                        if(tip_i > 0){
                            tip_i--;
                        }
                    }

                    /*如果是在按=之后输入退格键*/
                    else if(!equalsFlag) {
                        input.setText("");  //将显示器内容清空
                        inputControl = true;
                        tip_i = 0;
                        inputLock = true;
                    }
                }
            }

            /*如果输入的是CE键*/
            else if(command.compareTo("CE") == 0) {

                //将显示器内容设置为空
                input.setText("");
                //重新输入标志置为true
                inputControl = true;
                //缓存命令位数清0
                tip_i = 0;
                //表明可以继续输入
                inputLock = true;
                //表明输入=之前
                equalsFlag = true;
            }


            /*如果输入的是=号，并且输入合法*/
            else if(command.compareTo("=") == 0 && inputLock && isRight(str) && equalsFlag) {
                tip_i = 0;
                //表明不可以继续输入
                inputLock = false;
                //表明输入=之后
                equalsFlag = false;
                //保存原来算式样子
                str_old = str;
                //替换算式中的运算符，便于计算
                str = str.replaceAll("sin", "s");
                str = str.replaceAll("cos", "c");
                str = str.replaceAll("tan", "t");
                str = str.replaceAll("lg", "g");
                str = str.replaceAll("ln", "l");
                //重新输入标志设置true
                inputControl = true;
                //计算算式结果
                //Log.i(TAG,str_old);
                //Log.i(TAG,str);
                new Calculation().process(str);
            }
            //表明可以继续输入
            inputLock = true;
        }
    };

    /**
     * 打印函数
     * 向input输出字符
     */
    private void print(String str) {
        //清屏后输出
        if(inputControl){
            input.setText(str);
        }

        //在屏幕原str后增添字符
        else{
            input.append(str);
        }
        inputControl = false;
    }

    /**
     * 判断一个str是否是合法的，若合法则返回true，否则返回false
     * 只包含0123456789.()sincostanlnlg+-×÷^的是合法的str
     */
    private boolean isRight(String str) {
        int i;
        for(i = 0;i < str.length();i++) {
            if(str.charAt(i)!='0' && str.charAt(i)!='1' && str.charAt(i)!='2' &&
                    str.charAt(i)!='3' && str.charAt(i)!='4' && str.charAt(i)!='5' &&
                    str.charAt(i)!='6' && str.charAt(i)!='7' && str.charAt(i)!='8' &&
                    str.charAt(i)!='9' && str.charAt(i)!='.' && str.charAt(i)!='-' &&
                    str.charAt(i)!='+' && str.charAt(i)!='*' && str.charAt(i)!='/' &&
                    str.charAt(i)!='^' && str.charAt(i)!='s' && str.charAt(i)!='i' &&
                    str.charAt(i)!='n' && str.charAt(i)!='c' && str.charAt(i)!='o' &&
                    str.charAt(i)!='t' && str.charAt(i)!='a' && str.charAt(i)!='l' &&
                    str.charAt(i)!='g' && str.charAt(i)!='(' && str.charAt(i)!=')' )
                break;
        }
        if(i == str.length()) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 检测函数，返回值为3、2、1  表示应当一次删除几个字符
     * 为DEL按键的删除方式提供依据
     * 返回3，表示str尾部为sin、cos、tan中的一个，应当一次删除3个
     * 返回2，表示str尾部为ln,lg应当一次删除2个
     * 返回1，表示为除返回3、2外的所有情况，只需删除一个（包含非法字符时要另外考虑：应清屏）
     */
    private int delete(String str) {
        if((str.charAt(str.length() - 1) == 'n' &&
                str.charAt(str.length() - 2) == 'i' &&
                str.charAt(str.length() - 3) == 's') ||
                (str.charAt(str.length() - 1) == 's' &&
                        str.charAt(str.length() - 2) == 'o' &&
                        str.charAt(str.length() - 3) == 'c') ||
                (str.charAt(str.length() - 1) == 'n' &&
                        str.charAt(str.length() - 2) == 'a' &&
                        str.charAt(str.length() - 3) == 't')) {
            return 3;
        }
        else if((str.charAt(str.length() - 1) == 'n' && str.charAt(str.length() - 2) == 'l')
                || (str.charAt(str.length() - 1) == 'g' && str.charAt(str.length() - 2) == 'l')) {
            return 2;
        }
        else {
            return 1;
        }
    }



    /**
     * 整个计算核心，只要将表达式的整个字符串传入Calculation().process()就可以计算
     * 算法包括以下几部分：
     * 1、计算部分           process(String str)   在检查无错误的前提下
     * 2、数据格式化          accuracy(double n)         使数据有相当的精确度
     * 3、错误提示          showError(int code ,String str)  显示错误
     */
    public class Calculation {
        public Calculation(){
        }
        final int MAXLEN = 500;

        /*
         * 从左向右扫描表达式，数字入number栈，运算符入operator栈
         * +-的基本优先级为1，×÷的基本优先级为2，lg ln sin cos tan的基本优先级为3，^的基本优先级为4
         * 括号内层运算符比外层同级运算符优先级高4
         * 当前运算符优先级高于栈顶则压栈，低于栈顶弹出一个运算符与两个数进行运算
         * 重复直到当前运算符大于栈顶
         * 扫描完后对剩下的运算符与数字依次计算
         */
        public void process(String str) {
            int basicPriority = 0;     //basicPriority为同一（）下的基本优先级
            int tempPriority;         //tempPriority临时记录优先级的变化
            int topOp = 0;          //topOp为priority[]和operator[]的计数器
            int topNum = 0;         //topNum为number[]的计数器
            int flag = 1;           //flag为正负数的计数器，1为正数，-1为负数
            int priority[];           //保存operator栈中运算符的优先级，以topOp计数
            double number[];        //保存数字，以topNum计数
            char ch, ch_gai, operator[];//operator[]保存运算符，以topOp计数
            String num;       //记录数字，str以+-×÷()sctgl^分段，这些字符之间的字符串即为数字
            priority = new int[MAXLEN];
            number = new double[MAXLEN];
            operator = new char[MAXLEN];
            String expression = str;
            StringTokenizer expToken = new StringTokenizer(expression,"+-*/()sctgl^");
            int i = 0;

            //循环扫描
            while (i < expression.length()) {
                ch = expression.charAt(i);  //ch为当前字符

                //判断正负数
                if (i == 0) {
                    if (ch == '-'){
                        flag = -1;  //-出现在表达式首部，则说明该数为负数（因为减号不可能出现在表达式首部）
                    }
                }
                else if(expression.charAt(i-1) == '(' && ch == '-'){
                    flag = -1;      //左括号后边直接加-，也说明是负号不是减号
                }

                //取得数字，并将正负符号转移给数字
                if (ch <= '9' && ch >= '0'|| ch == '.') {
                    num = expToken.nextToken();     //被运算符切开的数字
                    ch_gai = ch;
                    //取得整个数字
                    while (i < expression.length() && (ch_gai <= '9' && ch_gai >= '0'|| ch_gai == '.'))
                    {
                        ch_gai = expression.charAt(i++);
                    }

                    //将指针退回之前的位置
                    if (i >= expression.length()) {
                        i-=1;
                    }
                    else{
                        i-=2;
                    }

                    if (num.compareTo(".") == 0) {
                        number[topNum++] = 0;
                    }
                    //将正负符号转移给数字
                    else {
                        number[topNum++] = Double.parseDouble(num)*flag;
                        flag = 1;
                    }
                }
                //计算运算符的优先级
                if (ch == '('){     //左括号优先级加4
                    basicPriority+=4;
                }
                if (ch == ')'){     //右括号优先级减4
                    basicPriority-=4;
                }

                if (ch == '-' && flag == 1 || ch == '+' || ch == '*'|| ch == '/' ||
                        ch == 's' ||ch == 'c' || ch == 't' || ch == 'g' || ch == 'l' ||
                        ch == '^') {
                    switch (ch) {
                        //+-的优先级最低，为1
                        case '+':
                        case '-':
                            tempPriority = 1 + basicPriority;        //basicPriority初值为0
                            break;
                        //x÷的优先级稍高，为2
                        case '*':
                        case '/':
                            tempPriority = 2 + basicPriority;
                            break;
                        //三角函数和对数的优先级为3
                        case 's':
                        case 'c':
                        case 't':
                        case 'g':
                        case 'l':
                            tempPriority = 3 + basicPriority;
                            break;
                        //其余优先级为4（即'^'）
                        default:
                            tempPriority = 4 + basicPriority;
                            break;
                    }
                    //如果operator栈为空或者当前运算符的优先级大于栈顶元素的优先级，则直接入栈
                    if (topOp == 0 || priority[topOp-1] < tempPriority) {
                        priority[topOp] = tempPriority;     //优先级数入栈
                        operator[topOp] = ch;           //运算符入栈
                        topOp++;                        //指针上移
                    }
                    //否则将operator栈中运算符逐个取出，直到栈顶运算符的优先级小于当前运算符
                    else {
                        while (topOp > 0 && priority[topOp-1] >= tempPriority) {    //一直循环
                            switch (operator[topOp-1]) {
                                //取出数字数组的相应元素（栈顶和栈顶下边的数）进行运算
                                case '+':
                                    number[topNum-2]+=number[topNum-1];     //两数相加
                                    break;
                                case '-':
                                    number[topNum-2]-=number[topNum-1];     //两数相减
                                    break;
                                case '*':
                                    number[topNum-2]*=number[topNum-1];     //两数相乘
                                    break;
                                //判断除数为0的情况
                                case '/':
                                    if (number[topNum-1] == 0) {
                                        showError(1,str_old);       //除数为0则报错
                                        return;
                                    }
                                    number[topNum-2]/=number[topNum-1];
                                    break;
                                case '^':
                                    number[topNum-2] = Math.pow(number[topNum-2], number[topNum-1]);
                                    break;
                                //sin
                                case 's':
                                    number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                                    topNum++;
                                    break;
                                //cos
                                case 'c':
                                    number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                                    topNum++;
                                    break;
                                //tan
                                case 't':
                                    if((Math.abs(number[topNum-1])/90)%2 == 1) {
                                        showError(2,str_old);   //若输入90度的奇数倍，则报错（函数格式错误）
                                        return;
                                    }
                                    number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                                    topNum++;
                                    break;
                                //lg
                                case 'g':
                                    if(number[topNum-1] <= 0) { //若常用对数的真数<=0，则报错（函数格式错误）
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1] = Math.log10(number[topNum-1]);
                                    topNum++;
                                    break;
                                //ln
                                case 'l':
                                    if(number[topNum-1] <= 0) { //若自然对数的真数<=0，则报错（函数格式错误）
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1] = Math.log(number[topNum-1]);
                                    topNum++;
                                    break;
                            }
                            //继续取出栈的下一个元素进行判断
                            topNum--;
                            topOp--;
                        }
                        //将运算符入栈
                        priority[topOp] = tempPriority;
                        operator[topOp] = ch;
                        topOp++;
                    }
                }
                i++;
            }
            //依次取出栈内的运算符进行运算（最后一次运算，必须进行）
            while (topOp>0) {
                //+-x直接将数组的后两位数取出运算
                switch (operator[topOp-1]) {
                    case '+':
                        number[topNum-2]+=number[topNum-1];
                        break;
                    case '-':
                        number[topNum-2]-=number[topNum-1];
                        break;
                    case '*':
                        number[topNum-2]*=number[topNum-1];
                        break;
                    //涉及到除法时要考虑除数不能为零的情况
                    case '/':
                        if (number[topNum-1] == 0) {
                            showError(1,str_old);
                            return;
                        }
                        number[topNum-2]/=number[topNum-1];
                        break;
                    case '^':
                        number[topNum-2] = Math.pow(number[topNum-2], number[topNum-1]);
                        break;
                    //sin
                    case 's':
                        number[topNum-1] = Math.sin((number[topNum-1]/180)*pi);
                        topNum++;
                        break;
                    //cos
                    case 'c':
                        number[topNum-1] = Math.cos((number[topNum-1]/180)*pi);
                        topNum++;
                        break;
                    //tan
                    case 't':
                        if((Math.abs(number[topNum-1])/90)%2 == 1) {
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1] = Math.tan((number[topNum-1]/180)*pi);
                        topNum++;
                        break;
                    //对数lg
                    case 'g':
                        if(number[topNum-1] <= 0) {
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1] = Math.log10(number[topNum-1]);
                        topNum++;
                        break;
                    //自然对数ln
                    case 'l':
                        if(number[topNum-1] <= 0) {
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1] = Math.log(number[topNum-1]);
                        topNum++;
                        break;
                }
                //取堆栈下一个元素计算
                topNum--;
                topOp--;
            }
            //如果是数字太大，提示错误信息
            if(number[0] > 7.3E306) {
                showError(3,str_old);
                return;
            }
            //输出最终结果
            input.setText(String.valueOf(accuracy(number[0])));
            memory.setText(str_old+"="+String.valueOf(accuracy(number[0])));
        }

        /**
         * accuracy控制小数的位数，达到精度
         * 否则会出现 0.6-0.2=0.39999999999999997的情况，用accuracy即可解决，使得数为0.4
         * 本格式精度为15位
         */
        public double accuracy(double n) {
            //NumberFormat format=NumberFormat.getInstance();  //创建一个格式化类f
            //format.setMaximumFractionDigits(18);    //设置小数位的格式
            DecimalFormat format = new DecimalFormat("0.#############");
            return Double.parseDouble(format.format(n));
        }


        /**
         * 错误提示，按了"="之后，若计算式在process()过程中，出现错误，则进行提示
         */
        public void showError(int code ,String str) {
            String message="";
            switch (code) {
                case 1:
                    message = "除数不能为0";
                    break;
                case 2:
                    message = "函数格式错误";
                    break;
                case 3:
                    message = "值太大溢出";
            }
            input.setText("\""+str+"\""+": "+message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.buttonHelp:
                Toast.makeText(MainActivity.this,"This is help",Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonExit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.buttonHelp:
                Toast.makeText(MainActivity.this,"This is help",Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonExit:
                finish();
                break;
        }
        return false;
    }
}