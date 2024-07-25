package main.java.dtdu.util;

import java.io.*;

public class DataWriter extends FileOutputStream {
	private DataWriter(File file) throws FileNotFoundException {
		super(file);
	}
	public static DataWriter create(String path) throws FileNotFoundException {
		return create(new File(path));
	}
	public static DataWriter create(File f) throws FileNotFoundException {
		if(f.exists()) f.delete();
		else if(!f.getParentFile().exists()) f.getParentFile().mkdirs();
		return new DataWriter(f);
	}
	public static byte[] writeBooleans(boolean[] b) {
		byte[] data = new byte[b.length / 8 + (b.length % 8 == 0 ? 0 : 1)];
		write(b, data, 0);
		return data;
	}
	public static void write(boolean[] b, byte[] data, int offset) {
		if(b != null) {
			byte bt;
			for(int k, i = 0; offset < data.length; offset++) {
				bt = 0;
				for(int j = 0; j < 8; j++) {
					k = (i << 3) + j;
					if(k >= b.length) return;
					else if(b[k]) bt = (byte) (bt | (1 << j));
				} data[offset] = bt;
			}
		}
	}
	public void write(boolean[] b) throws IOException {
		if(b != null) {
			byte[] output = new byte[b.length / 8 + (b.length % 8 == 0 ? 0 : 1)];
			byte bt;
			for(int i = 0, k; i < output.length; i++) {
				bt = 0;
				for(int j = 0; j < 8; j++) {
					k = (i << 3) + j;
					if(k < b.length && b[k]) bt = (byte) (bt | (1 << j));
				} output[i] = bt;
			} write(output);
		}
	}
	public static byte[] writeBoolean(boolean b) {
		return new byte[] {b ? (byte)1 : 0};
	}
	public static void write(boolean b, byte[] data, int offset) {
		data[offset] = b ? (byte)1 : 0;
	}
	public void write(boolean b) throws IOException {
		super.write(b ? 1 : 0);
	}
	public void write(byte b) throws IOException {
		super.write(b);
	}
	public static byte[] writeChars(char[] c) {
		byte[] data = new byte[c.length << 1];
		write(c, data, 0);
		return data;
	}
	public static void write(char[] c, byte[] data, int offset) {
		if(c != null) {
			char ch;
			for(int i = 0; i < c.length; i++) {
				data[offset++] = (byte)((ch = c[i]) >> 8);
				data[offset++] = (byte)ch;
			}
		}
	}
	public void write(char[] c) throws IOException {
		if(c != null) {
			byte[] data = new byte[c.length << 1];
			char ch;
			for(int i = 0, j = 0; i < c.length; i++) {
				data[j++] = (byte)((ch = c[i]) >> 8);
				data[j++] = (byte)ch;
			} write(data);
		}
	}
	public static byte[] writeChar(char c) {
		return new byte[] {(byte)(c >> 8),(byte)c};
	}
	public static void write(char c, byte[] data, int offset) {
		data[offset++] = (byte)(c >> 8);
		data[offset] = (byte)c;
	}
	public void write(char c) throws IOException {
		write(new byte[] {(byte)(c >> 8),(byte)c});
	}
	public static byte[] writeShorts(short[] s) {
		byte[] data = new byte[s.length << 1];
		write(s, data, 0);
		return data;
	}
	public static void write(short[] s, byte[] data, int offset) {
		if(s != null) {
			short ch;
			for(int i = 0; i < s.length; i++) {
				data[offset++] = (byte)((ch = s[i]) >> 8);
				data[offset++] = (byte)ch;
			}
		}
	}
	public void write(short[] s) throws IOException {
		if(s != null) {
			byte[] data = new byte[s.length << 1];
			short ch;
			for(int i = 0, j = 0; i < s.length; i++) {
				data[j++] = (byte)((ch = s[i]) >> 8);
				data[j++] = (byte)ch;
			} write(data);
		}
	}
	public static byte[] writeShort(short s) {
		return new byte[] {(byte)(s >> 8),(byte)s};
	}
	public static void write(short s, byte[] data, int offset) {
		data[offset++] = (byte)(s >> 8);
		data[offset] = (byte)s;
	}
	public void write(short s) throws IOException {
		write(new byte[] {(byte)(s >> 8),(byte)s});
	}
	public static byte[] writeInts(int[] i) {
		byte[] data = new byte[i.length << 2];
		write(i, data, 0);
		return data;
	}
	public static void write(int[] i, byte[] data, int offset) {
		if(i != null) for(int j = 0, l; j < i.length; j++) {
			data[offset++] = (byte)((l = i[j]) >> 24);
			data[offset++] = (byte)(l >> 16);
			data[offset++] = (byte)(l >> 8);
			data[offset++] = (byte)l;
		}
	}
	public void write(int[] i) throws IOException {
		if(i != null) {
			byte[] data = new byte[i.length << 2];
			for(int j = 0, k = 0, l; j < i.length; j++) {
				data[k++] = (byte)((l = i[j]) >> 24);
				data[k++] = (byte)(l >> 16);
				data[k++] = (byte)(l >> 8);
				data[k++] = (byte)l;
			} write(data);
		}
	}
	public static byte[] writeInt(int i) {
		return new byte[] {(byte)(i >> 24),(byte)(i >> 16),(byte)(i >> 8),(byte)i};
	}
	public static void write(int i, byte[] data, int offset) {
		data[offset++] = (byte)(i >> 24);
		data[offset++] = (byte)(i >> 16);
		data[offset++] = (byte)(i >> 8);
		data[offset] = (byte)i;
	}
	public void write(int i) throws IOException {
		write(new byte[] {(byte)(i >> 24),(byte)(i >> 16),(byte)(i >> 8),(byte)i});
	}
	public static void write(float[] f, byte[] data, int offset) {
		if(f != null) for(int i = 0, k; i < f.length; i++) {
			data[offset++] = (byte)((k = Float.floatToRawIntBits(f[i])) >> 24);
			data[offset++] = (byte)(k >> 16);
			data[offset++] = (byte)(k >> 8);
			data[offset++] = (byte)k;
		}
	}
	public void write(float[] f) throws IOException {
		if(f != null) {
			byte[] data = new byte[f.length << 2];
			for(int i = 0, j = 0, k; i < f.length; i++) {
				data[j++] = (byte)((k = Float.floatToRawIntBits(f[i])) >> 24);
				data[j++] = (byte)(k >> 16);
				data[j++] = (byte)(k >> 8);
				data[j++] = (byte)k;
			} write(data);
		}
	}
	public static byte[] writeFloat(float f) {
		return writeInt(Float.floatToRawIntBits(f));
	}
	public static void write(float f, byte[] data, int offset) {
		write(Float.floatToRawIntBits(f), data, offset);
	}
	public void write(float f) throws IOException {
		write(Float.floatToRawIntBits(f));
	}
	public static void write(long[] l, byte[] data, int offset) {
		if(l != null) {
			long lo;
			for(int i = 0; i < l.length; i++) {
				data[offset++] = (byte)((lo = l[i]) >> 56L);
				data[offset++] = (byte)(lo >> 48L);
				data[offset++] = (byte)(lo >> 40L);
				data[offset++] = (byte)(lo >> 32L);
				data[offset++] = (byte)(lo >> 24L);
				data[offset++] = (byte)(lo >> 16L);
				data[offset++] = (byte)(lo >> 8L);
				data[offset++] = (byte)lo;
			}
		}
	}
	public void write(long[] l) throws IOException {
		if(l != null) {
			byte[] data = new byte[l.length << 3];
			long lo;
			for(int i = 0, j = 0; i < l.length; i++) {
				data[j++] = (byte)((lo = l[i]) >> 56L);
				data[j++] = (byte)(lo >> 48L);
				data[j++] = (byte)(lo >> 40L);
				data[j++] = (byte)(lo >> 32L);
				data[j++] = (byte)(lo >> 24L);
				data[j++] = (byte)(lo >> 16L);
				data[j++] = (byte)(lo >> 8L);
				data[j++] = (byte)lo;
			} write(data);
		}
	}
	public static byte[] writeLong(long l) {
		return new byte[] {(byte)(l >> 56L),(byte)(l >> 48L),(byte)(l >> 40L),(byte)(l >> 32L),(byte)(l >> 24L),(byte)(l >> 16L),(byte)(l >> 8L),(byte)l};
	}
	public static void write(long l, byte[] data, int offset) {
		data[offset++] = (byte)(l >> 56L);
		data[offset++] = (byte)(l >> 48L);
		data[offset++] = (byte)(l >> 40L);
		data[offset++] = (byte)(l >> 32L);
		data[offset++] = (byte)(l >> 24L);
		data[offset++] = (byte)(l >> 16L);
		data[offset++] = (byte)(l >> 8L);
		data[offset] = (byte)l;
	}
	public void write(long l) throws IOException {
		write(new byte[] {(byte)(l >> 56L),(byte)(l >> 48L),(byte)(l >> 40L),(byte)(l >> 32L),(byte)(l >> 24L),(byte)(l >> 16L),(byte)(l >> 8L),(byte)l});
	}
	public static void write(double[] d, byte[] data, int offset) {
		if(d != null) {
			long lo;
			for(int i = 0; i < d.length; i++) {
				data[offset++] = (byte)((lo = Double.doubleToRawLongBits(d[i])) >> 56L);
				data[offset++] = (byte)(lo >> 48L);
				data[offset++] = (byte)(lo >> 40L);
				data[offset++] = (byte)(lo >> 32L);
				data[offset++] = (byte)(lo >> 24L);
				data[offset++] = (byte)(lo >> 16L);
				data[offset++] = (byte)(lo >> 8L);
				data[offset++] = (byte)lo;
			}
		}
	}
	public void write(double[] d) throws IOException {
		if(d != null) {
			byte[] data = new byte[d.length << 3];
			long lo;
			for(int i = 0, j = 0; i < d.length; i++) {
				data[j++] = (byte)((lo = Double.doubleToRawLongBits(d[i])) >> 56L);
				data[j++] = (byte)(lo >> 48L);
				data[j++] = (byte)(lo >> 40L);
				data[j++] = (byte)(lo >> 32L);
				data[j++] = (byte)(lo >> 24L);
				data[j++] = (byte)(lo >> 16L);
				data[j++] = (byte)(lo >> 8L);
				data[j++] = (byte)lo;
			} write(data);
		}
	}
	public static byte[] writeDouble(double d) {
		return writeLong(Double.doubleToRawLongBits(d));
	}
	public static void write(double d, byte[] data, int offset) {
		write(Double.doubleToRawLongBits(d), data, offset);
	}
	public void write(double d) throws IOException {
		write(Double.doubleToRawLongBits(d));
	}
}