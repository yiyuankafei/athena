package athena.io.nio.application;

import java.nio.IntBuffer;

public class TestBuffer {
	
	public static void main(String[] args) {
		//1.基本操作
		
		//创建指定长度的缓冲区
		IntBuffer buffer = IntBuffer.allocate(10);
		buffer.put(13); //position位置: 0 -> 1
		buffer.put(21); //position位置: 1 -> 2
		buffer.put(35); //position位置: 2 -> 3
		buffer.flip(); //把位置复位为0： 3 -> 0
		System.out.println("使用flip复位：" + buffer);
		System.out.println("容量为：" + buffer.capacity()); //容量一旦初始化后不允许改变（wrap方法包含数组除外）
		System.out.println("限制为:" + buffer.limit()); //由于只装载了3个元素，所以可读或者操作的元素为3 则 limit = 3
		
		System.out.println("获取下标为1的元素：" + buffer.get(1));
		System.out.println("get(index)方法，position位置不变：" + buffer);
		buffer.put(1,4);
		System.out.println("put(index, change)方法，position位置不变:" + buffer);
		
		for (int i = 0; i < buffer.limit(); i++) {
			System.out.println(buffer.get() + "\t");
			System.out.println(buffer);
		}
		System.out.println("buffer对象遍历之后：" + buffer);
		
		//2. wrap方法使用
		//wrap会包裹一个数组：一般这种用法不会先初始化缓存数组的长度，因为没有意义，最后还是会被包裹数组的长度覆盖
		//并且wrap方法修改缓冲区对象的时候，数组本身也会跟着发生变化
		int[] arr = new int[]{1,2,5,7,8};
		IntBuffer buffer1 = IntBuffer.wrap(arr);
		System.out.println(buffer1);
		
		IntBuffer buffer2 = IntBuffer.wrap(arr, 1, 2);
		System.out.println(buffer2);
		
		System.out.println("==================");
		//3.其他方法
		IntBuffer buffer3 = IntBuffer.allocate(10);
		int[] arr3 = new int[]{1,2,5};
		buffer3.put(arr3);
		System.out.println(buffer3);
		//一种复制方法
		IntBuffer buffer4 = buffer3.duplicate();
		System.out.println(buffer4);
		
		//复制buffer位置属性
		buffer3.position(1);
		System.out.println(buffer3);
		
		System.out.println("可读数据为：" + buffer3.remaining());
		
		int[] arr4 = new int[(buffer3.remaining())];
		//将缓冲区数据写入数组
		buffer3.get(arr4);
		for (int i : arr4) {
			System.out.print(Integer.toString(i) + ",");
		}
		
		
		
		
	}

}
