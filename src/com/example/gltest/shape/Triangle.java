package com.example.gltest.shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle implements Shape {
	private FloatBuffer vertexBuffer;
	// We intend to draw a triangle here
	private static float[] coordinates = { 0.0f, 0.1f, 0.0f,// top center
			-1.0f, -1.0f, 0.0f,// button left
			1.0f, -1.0f, 0.0f // button right
	};
	public Triangle() {
		// make byte buffer for the triangle
		ByteBuffer byteBuffer = ByteBuffer
				.allocateDirect(coordinates.length * 4);
		// set order of reading as native
		byteBuffer.order(ByteOrder.nativeOrder());
		// cast to float buffer
		vertexBuffer = byteBuffer.asFloatBuffer();
		// put vertex in buffer
		vertexBuffer.put(coordinates);
		// set position for start reading
		vertexBuffer.position(0);
	}

	@Override
	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// set the colour for the square
		gl.glColor4f(0.0f, 1.0f, 0.0f, 0.5f);
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		//gl.glRotatef(0.1f, 0.01f, 0.0f, 0.0f);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
