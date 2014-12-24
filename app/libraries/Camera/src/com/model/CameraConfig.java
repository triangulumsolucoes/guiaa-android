package com.model;

import android.widget.ImageView;

public class CameraConfig {

	private int resultCodeCamera;
	private int resultCodeGaleria;
	private ImageView imageView;
	private int largura;
	private int altura;

	public CameraConfig(ImageView imageView, int resultCodeCamera, int resultCodeGaleria,
			int largura, int altura) {
		super();
		this.imageView = imageView;
		this.resultCodeCamera = resultCodeCamera;
		this.resultCodeGaleria = resultCodeGaleria;
		this.largura = largura;
		this.altura = altura;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	public int getResultCodeCamera() {
		return resultCodeCamera;
	}

	public void setResultCodeCamera(int resultCodeCamera) {
		this.resultCodeCamera = resultCodeCamera;
	}

	public int getResultCodeGaleria() {
		return resultCodeGaleria;
	}

	public void setResultCodeGaleria(int resultCodeGaleria) {
		this.resultCodeGaleria = resultCodeGaleria;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
