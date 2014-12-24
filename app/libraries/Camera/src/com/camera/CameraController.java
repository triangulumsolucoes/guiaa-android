package com.camera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.CameraConfig;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

public class CameraController {

	private CameraConfig cameraConfig;

	private String currentPhotoPath;

	public CameraController(CameraConfig cameraConfig) {
		this.cameraConfig = cameraConfig;
	}

	public Bitmap getImagemCamera() {
		Bitmap bitmap = null;

		if (currentPhotoPath != null) {
			ImageView imagemCamera = cameraConfig.getImageView();

			int targetW = imagemCamera.getWidth();
			int targetH = imagemCamera.getHeight();

			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			bmOptions.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
			int photoW = bmOptions.outWidth;
			int photoH = bmOptions.outHeight;

			int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

			bmOptions.inJustDecodeBounds = false;
			bmOptions.inSampleSize = scaleFactor;
			bmOptions.inPurgeable = true;

			bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
			deletarArquivo(currentPhotoPath);
		}

		return bitmap;
	}

	public Bitmap getImagemGaleria(Intent data) {
		if (data != null) {
			try {
				return data.getExtras().getParcelable("data");
			} catch (Exception e) {
			}
		}
		return null;
	}

	private void deletarArquivo(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	private File createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, ".jpg", storageDir);
		currentPhotoPath = image.getAbsolutePath();
		return image;
	}

	public void abrirCamera(Activity activity) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File imagem = null;
		try {
			imagem = createImageFile();
			if (imagem != null) {
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagem));
				intent.putExtra("crop", "true");
				intent.putExtra("aspectX", 0);
				intent.putExtra("aspectY", 0);
				intent.putExtra("outputX", cameraConfig.getLargura());
				intent.putExtra("outputY", cameraConfig.getAltura());
				activity.startActivityForResult(intent,
						cameraConfig.getResultCodeCamera());
			}
		} catch (IOException ex) {
		}
	}

	public void abrirGaleria(Activity activity) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 0);
		intent.putExtra("aspectY", 0);
		intent.putExtra("outputX", cameraConfig.getLargura());
		intent.putExtra("outputY", cameraConfig.getAltura());

		try {
			intent.putExtra("return-data", true);
			activity.startActivityForResult(
					Intent.createChooser(intent, "Escolha"),
					cameraConfig.getResultCodeGaleria());
		} catch (ActivityNotFoundException e) {
		}
	}
}
