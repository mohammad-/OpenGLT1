package com.example.gltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity implements Renderer {
	private FloatBuffer vertexBuffer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		GLSurfaceView surfaceView = new GLSurfaceView(this);
		// set renderer
		surfaceView.setRenderer(this);
		// set render mode
		surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		// set as content view
		setContentView(surfaceView);		
	}

	/**
	 * called first
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// set background color
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepthf(GL10.GL_DEPTH_BITS);
		// We intend to draw a triangle here
		float[] coordinates = { 0.0f, 0.1f, 0.0f,// top center
				-1.0f, -1.0f, 0.0f,// button left
				1.0f, -1.0f, 0.0f // button right
		};
		// make byte buffer for the triangle
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(coordinates.length * 4);
		// set order of reading as native
		byteBuffer.order(ByteOrder.nativeOrder());
		// cast to float buffer
		vertexBuffer = byteBuffer.asFloatBuffer();
		// put vertex in buffer
		vertexBuffer.put(coordinates);
		// set position for start reading
		vertexBuffer.position(0);
	}

	/**
	 * called second
	 */
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
	}

	/**
	 * called all the time
	 */
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// Redraw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glRotatef(0.1f, 0.01f, 0.0f, 0.0f);
		// set the colour for the square
		gl.glColor4f(0.0f, 1.0f, 0.0f, 0.5f);
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
