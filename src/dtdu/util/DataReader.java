package dtdu.util;

import java.io.*;

public class DataReader extends FileInputStream {
	private DataReader(File file) throws FileNotFoundException {
		super(file);
	}
	public static DataReader create(File file) throws FileNotFoundException {
		return file.exists() ? new DataReader(file) : null;
	}
	public static DataReader create(String path) throws FileNotFoundException {
		return create(new File(path));
	}
	public static boolean[] readBooleans(byte[] data) {
		return readBooleans(new boolean[data.length << 3], data, 0);
	}
	public static boolean[] readNBooleans(int length, byte[] data, int offset) {
		return readBooleans(new boolean[length], data, offset);
	}
	public static boolean[] readBooleans(boolean[] b, byte[] data, int offset) {
		if(b != null && data != null) {
			for(int k, i = 0; offset < data.length; i++, offset++) for(int j = 0; j < 8; j++) {
				k = (i << 3) + j;
				if(k < b.length && (data[offset] & (1 << j)) != 0) b[k] = true;
				else return b;
			}
		} return b;
	}
	public boolean[] readNBooleans(int length) throws IOException {
		return readBooleans(new boolean[length]);
	}
	public boolean[] readBooleans(boolean[] b) throws IOException {
		if(b != null) {
			int k = b.length / 8 + (b.length % 8 == 0 ? 0 : 1);
			byte[] data = readNBytes(k);
			for(int i = 0; i < data.length; i++) for(int j = 0; j < 8; j++) {
				k = (i << 3) + j;
				if(k < b.length && (data[i] & (1 << j)) != 0) b[k] = true;
				else return b;
			}
		} return b;
	}
	public static boolean readBoolean(byte data) {
		return data != 0;
	}
	public boolean readBoolean() throws IOException {
		int i = read();
		if(i == -1) throw new EndOfStreamException();
		return i != 0;
	}
	@Override @Deprecated
	public int read() throws IOException {
		return super.read();
	}
	@Override
	public byte[] readNBytes(int len) throws IOException {
		byte[] data = super.readNBytes(len);
		if(data.length < len) throw new EndOfStreamException();
		return data;
	}
	public byte readByte() throws IOException {
		int i = read();
		if(i == -1) throw new EndOfStreamException();
		return (byte)i;
	}
	public static char[] readChars(byte[] data) {
		return readChars(new char[data.length >> 1], data, 0);
	}
	public static char[] readNChars(int length, byte[] data, int offset) {
		return readChars(new char[length], data, offset);
	}
	public static char[] readChars(char[] c, byte[] data, int offset) {
		if(c != null) for(int j = 0; j < c.length; j++) c[j] = (char)((Byte.toUnsignedInt(data[offset++]) << 8 | Byte.toUnsignedInt(data[offset++])));
		return c;
	}
	public char[] readNChars(int length) throws IOException {
		return readChars(new char[length]);
	}
	public char[] readChars(char[] c) throws IOException {
		if(c != null) {
			byte[] data = readNBytes(c.length << 1);
			for(int i = 0, j = -1; i < c.length; i++) c[i] = (char)((Byte.toUnsignedInt(data[++j]) << 8 | Byte.toUnsignedInt(data[++j])));
		} return c;
	}
	public static char readChar(byte[] data, int offset) {
		return (char) ((Byte.toUnsignedInt(data[offset++]) << 8) | Byte.toUnsignedInt(data[offset]));
	}
	public char readChar() throws IOException {
		byte[] data = readNBytes(2);
		return (char) ((Byte.toUnsignedInt(data[0]) << 8) | Byte.toUnsignedInt(data[1]));
	}
	public static short[] readShorts(byte[] data) {
		return readShorts(new short[data.length >> 1], data, 0);
	}
	public static short[] readNShorts(int length, byte[]data, int offset) {
		return readShorts(new short[length], data, offset);
	}
	public static short[] readShorts(short[] s, byte[] data, int offset) {
		if(s != null) for(int j = 0; j < s.length; j++) s[j] = (short)((Byte.toUnsignedInt(data[offset++]) << 8 | Byte.toUnsignedInt(data[offset++])));
		return s;
	}
	public short[] readNShorts(int length) throws IOException {
		return readShorts(new short[length]);
	}
	public short[] readShorts(short[] s) throws IOException {
		if(s != null) {
			byte[] data = readNBytes(s.length << 1);
			for(int i = 0, j = -1; i < s.length; i++) s[i] = (short)((Byte.toUnsignedInt(data[++j]) << 8 | Byte.toUnsignedInt(data[++j])));
		} return s;
	}
	public static short readShort(byte[] data, int offset) {
		return (short) ((Byte.toUnsignedInt(data[offset++]) << 8) | Byte.toUnsignedInt(data[offset]));
	}
	public short readShort() throws IOException {
		byte[] data = readNBytes(2);
		return (short) ((Byte.toUnsignedInt(data[0]) << 8) | Byte.toUnsignedInt(data[1]));
	}
	public static int[] readInts(byte[] data) {
		return readInts(new int[data.length >> 2], data, 0);
	}
	public static int[] readNInts(int length, byte[] data, int offset) {
		return readInts(new int[length], data, offset);
	}
	public static int[] readInts(int[] i, byte[] data, int offset) {
		if(i != null) for(int k = 0; k < i.length; k++) i[k] = (Byte.toUnsignedInt(data[offset++]) << 24) | (Byte.toUnsignedInt(data[offset++]) << 16) | (Byte.toUnsignedInt(data[offset++]) << 8) | Byte.toUnsignedInt(data[offset++]);
		return i;
	}
	public int[] readNInts(int length) throws IOException {
		return readInts(new int[length]);
	}
	public int[] readInts(int[] i) throws IOException {
		if(i != null) {
			byte[] data = readNBytes(i.length << 2);
			for(int j = 0, k = -1; j < i.length; j++) i[j] = (Byte.toUnsignedInt(data[++k]) << 24) | (Byte.toUnsignedInt(data[++k]) << 16) | (Byte.toUnsignedInt(data[++k]) << 8) | Byte.toUnsignedInt(data[++k]);
		} return i;
	}
	public static int readInt(byte[] data, int offset) {
		return (Byte.toUnsignedInt(data[offset++]) << 24) | (Byte.toUnsignedInt(data[offset++]) << 16) | (Byte.toUnsignedInt(data[offset++]) << 8) | Byte.toUnsignedInt(data[offset]);
	}
	public int readInt() throws IOException {
		byte[] data = readNBytes(4);
		return (Byte.toUnsignedInt(data[0]) << 24) | (Byte.toUnsignedInt(data[1]) << 16) | (Byte.toUnsignedInt(data[2]) << 8) | Byte.toUnsignedInt(data[3]);
	}
	public static float[] readFloats(byte[] data) {
		return readFloats(new float[data.length >> 2], data, 0);
	}
	public static float[] readNFloats(int length, byte[] data, int offset) {
		return readFloats(new float[length], data, offset);
	}
	public static float[] readFloats(float[] f, byte[] data, int offset) {
		if(f != null) for(int i = 0; i < f.length; i++) f[i] = Float.intBitsToFloat((Byte.toUnsignedInt(data[offset++]) << 24) | (Byte.toUnsignedInt(data[offset++]) << 16) | (Byte.toUnsignedInt(data[offset++]) << 8) | Byte.toUnsignedInt(data[offset++]));
		return f;
	}
	public float[] readNFloats(int length) throws IOException {
		return readFloats(new float[length]);
	}
	public float[] readFloats(float[] f) throws IOException {
		if(f != null) {
			byte[] data = readNBytes(f.length << 2);
			for(int i = 0, j = -1; i < f.length; i++) f[i] = Float.intBitsToFloat((Byte.toUnsignedInt(data[++j]) << 24) | (Byte.toUnsignedInt(data[++j]) << 16) | (Byte.toUnsignedInt(data[++j]) << 8) | Byte.toUnsignedInt(data[++j]));
		} return f;
	}
	public static float readFloat(byte[] data, int offset) {
		return Float.intBitsToFloat(readInt(data, offset));
	}
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt());
	}
	public static long[] readLongs(byte[] data) {
		return readLongs(new long[data.length >> 3], data, 0);
	}
	public static long[] readNLongs(int length, byte[] data, int offset) {
		return readLongs(new long[length], data, offset);
	}
	public static long[] readLongs(long[] l, byte[] data, int offset) {
		if(l != null) for(int i = 0; i < l.length; i++) l[i] = (Byte.toUnsignedLong(data[offset++]) << 56L) | (Byte.toUnsignedLong(data[offset++]) << 48L) | (Byte.toUnsignedLong(data[offset++]) << 40L) | (Byte.toUnsignedLong(data[offset++]) << 32L) | (Byte.toUnsignedLong(data[offset++]) << 24L) | (Byte.toUnsignedLong(data[offset++]) << 16L) | (Byte.toUnsignedLong(data[offset++]) << 8L) | Byte.toUnsignedLong(data[offset]);
		return l;
	}
	public long[] readNLongs(int length) throws IOException {
		return readLongs(new long[length]);
	}
	public long[] readLongs(long[] l) throws IOException {
		if(l != null) {
			byte[] data = readNBytes(l.length << 3);
			for(int i = 0, j = -1; i < l.length; i++) l[i] = (Byte.toUnsignedLong(data[++j]) << 56L) | (Byte.toUnsignedLong(data[++j]) << 48L) | (Byte.toUnsignedLong(data[++j]) << 40L) | (Byte.toUnsignedLong(data[++j]) << 32L) | (Byte.toUnsignedLong(data[++j]) << 24L) | (Byte.toUnsignedLong(data[++j]) << 16L) | (Byte.toUnsignedLong(data[++j]) << 8L) | Byte.toUnsignedLong(data[++j]);
		} return l;
	}
	public static long readLong(byte[] data, int offset) {
		return (Byte.toUnsignedLong(data[offset++]) << 56L) | (Byte.toUnsignedLong(data[offset++]) << 48L) | (Byte.toUnsignedLong(data[offset++]) << 40L) | (Byte.toUnsignedLong(data[offset++]) << 32L) | (Byte.toUnsignedLong(data[offset++]) << 24L) | (Byte.toUnsignedLong(data[offset++]) << 16L) | (Byte.toUnsignedLong(data[offset++]) << 8L) | Byte.toUnsignedLong(data[offset]);
	}
	public long readLong() throws IOException {
		byte[] data = readNBytes(8);
		return (Byte.toUnsignedLong(data[0]) << 56L) | (Byte.toUnsignedLong(data[1]) << 48L) | (Byte.toUnsignedLong(data[2]) << 40L) | (Byte.toUnsignedLong(data[3]) << 32L) | (Byte.toUnsignedLong(data[4]) << 24L) | (Byte.toUnsignedLong(data[5]) << 16L) | (Byte.toUnsignedLong(data[6]) << 8L) | Byte.toUnsignedLong(data[7]);
	}
	public static double[] readDoubles(double[] d, byte[] data, int offset) {
		if(d != null) for(int i = 0; i < d.length; i++) d[i] = Double.longBitsToDouble((Byte.toUnsignedLong(data[offset++]) << 56L) | (Byte.toUnsignedLong(data[offset++]) << 48L) | (Byte.toUnsignedLong(data[offset++]) << 40L) | (Byte.toUnsignedLong(data[offset++]) << 32L) | (Byte.toUnsignedLong(data[offset++]) << 24L) | (Byte.toUnsignedLong(data[offset++]) << 16L) | (Byte.toUnsignedLong(data[offset++]) << 8L) | Byte.toUnsignedLong(data[offset++]));
		return d;
	}
	public double[] readNDoubles(int length) throws IOException {
		return readDoubles(new double[length]);
	}
	public double[] readDoubles(double[] d) throws IOException {
		if(d != null) {
			byte[] data = readNBytes(d.length << 3);
			for(int i = 0, j = -1; i < d.length; i++) d[i] = Double.longBitsToDouble((Byte.toUnsignedLong(data[++j]) << 56L) | (Byte.toUnsignedLong(data[++j]) << 48L) | (Byte.toUnsignedLong(data[++j]) << 40L) | (Byte.toUnsignedLong(data[++j]) << 32L) | (Byte.toUnsignedLong(data[++j]) << 24L) | (Byte.toUnsignedLong(data[++j]) << 16L) | (Byte.toUnsignedLong(data[++j]) << 8L) | Byte.toUnsignedLong(data[++j]));
		} return d;
	}
	public static double readDouble(byte[] data, int offset) {
		return Double.longBitsToDouble(readLong(data, offset));
	}
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}
}