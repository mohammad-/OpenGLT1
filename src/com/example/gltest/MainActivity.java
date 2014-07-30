package com.example.gltest;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.gltest.shape.Rectangle;
import com.example.gltest.shape.Shape;
import com.example.gltest.shape.Triangle;

public class MainActivity extends Activity implements Renderer {
	private Shape triangle;
	private Shape rectangle;
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
		gl.glClearDepthf(GL10.GL_DEPTH_BUFFER_BIT);
		triangle = new Triangle();
		rectangle = new Rectangle();
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
		// Redraw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);		
		triangle.draw(gl);
		rectangle.draw(gl);
	}
}
