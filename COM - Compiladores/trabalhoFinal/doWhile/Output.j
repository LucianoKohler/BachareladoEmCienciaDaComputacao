.class public Output
.super java/lang/Object

.field public static read Ljava/util/Scanner;

.method public <init>()V
	.limit stack 1
	.limit locals 1
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method static <clinit>()V
	.limit stack 3
	.limit locals 0
	new java/util/Scanner
	dup
	getstatic java/lang/System/in Ljava/io/InputStream;
	invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
	putstatic Output/read Ljava/util/Scanner;
	return
.end method

.method public static menor(DD)D
	.limit stack 20
	.limit locals 10
	dconst_0
	dstore 4
	dconst_0
	dstore 6
	dconst_0
	dstore 8
	dload_0
	dload_2
	dcmpg
	iflt L2
	goto L3
L2:
	dload_0
	dstore 8
	goto L4
L3:
	dload_2
	dstore 8
L4:
	dload 8
	dreturn
.end method

.method public static fat(I)I
	.limit stack 20
	.limit locals 3
	iconst_0
	istore_1
	iconst_0
	istore_2
	iconst_1
	istore_2
L5:
	iload_0
	iconst_0
	if_icmpgt L6
	goto L7
L6:
	iload_2
	iload_0
	imul
	istore_2
	iload_0
	iconst_1
	isub
	istore_0
	goto L5
L7:
	iload_2
	ireturn
.end method

.method public static somatorio(I)I
	.limit stack 20
	.limit locals 5
	iconst_0
	istore_1
	iconst_0
	istore_2
	dconst_0
	dstore_3
	iconst_0
	i2d
	dstore_3
	iconst_0
	istore_2
L8:
	iload_2
	iload_0
	if_icmplt L9
	goto L10
L9:
	dload_3
	iload_2
	i2d
	dadd
	dstore_3
	iload_2
	iconst_1
	iadd
	istore_2
	goto L8
L10:
	dload_3
	d2i
	ireturn
.end method

.method public static imprimir(Ljava/lang/String;D)V
	.limit stack 20
	.limit locals 6
	ldc ""
	astore_3
	dconst_0
	dstore 4
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload_0
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	dload_1
	invokevirtual java/io/PrintStream/println(D)V
	return
	return
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 20
	.limit locals 5

	iconst_0
	istore_1
	iconst_0
	istore_2
	dconst_0
	dstore_3
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "Numero:"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic Output/read Ljava/util/Scanner;
	invokevirtual java/util/Scanner/nextInt()I
	istore_2
	iconst_1
	i2d
	dstore_3
L0:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	dload_3
	invokevirtual java/io/PrintStream/println(D)V
	dload_3
	iconst_1
	i2d
	dadd
	dstore_3
	dload_3
	iconst_5
	i2d
	dcmpg
	iflt L0
	goto L1
L1:
	ldc "Fatorial:"
	iload_2
	invokestatic Output/fat(I)I
	i2d
	invokestatic Output/imprimir(Ljava/lang/String;D)V
	ldc "Menor:"
	dload_3
	invokestatic Output/imprimir(Ljava/lang/String;D)V
	return
	return
.end method

