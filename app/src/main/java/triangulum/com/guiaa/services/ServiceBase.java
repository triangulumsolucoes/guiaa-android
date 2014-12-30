package triangulum.com.guiaa.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.KeyEvent;

public class ServiceBase extends AsyncTask<Void, Void, Void> {

	private static int TIME_FOR_TIMEOUT = 30000;
	
	private Context context;
	private ProgressDialog dialog;
	private HttpResponse response;
	private DefaultHttpClient client;
	private boolean mBackPressed;

	protected boolean running = true;

	public ServiceBase(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	public void montaDialogFeedback(String feedback, boolean cancelable) {
		if (context != null) {
			dialog = new ProgressDialog(context);
			dialog.setMessage(feedback);
			// dialog.setContentView(R.layout.elemento_dialog);
			dialog.setCancelable(cancelable);
			if (dialog != null) {
				dialog.show();
			}
		} else {
			cancel(true);
		}

		cancelableController(cancelable);
	}

	private void cancelableController(boolean cancelable) {
		if (cancelable) {
			dialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					onCancelled();
				}
			});
			dialog.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					if (mBackPressed) {
						onCancelled();
					}
				}
			});
			dialog.setOnKeyListener(new ProgressDialog.OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						mBackPressed = true;
					}
					return false;
				}
			});
		}
	}

	public void dismissDialog() {
		if (dialog != null && dialog.isShowing() && context != null) {
			dialog.dismiss();
		}
	}

	@Override
	protected Void doInBackground(Void... params) {
		return null;
	}

	@Override
	protected void onCancelled() {
		stopRequest();
		running = false;
	}

	public JSONObject doPost(String url, String body)
			throws ClientProtocolException, IOException {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIME_FOR_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_FOR_TIMEOUT);
		client = new DefaultHttpClient(httpParameters);
		HttpPost req = new HttpPost(url);
		req.setEntity(new StringEntity(body));
		req.addHeader("Content-type", "application/json");
		req.addHeader("Accept", "application/json");
		response = client.execute(req);

		if (response != null) {
			return jsonParseObject(response.getEntity());
		}
		return null;
	}

    public String doPost(String url)
            throws ClientProtocolException, IOException {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIME_FOR_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, TIME_FOR_TIMEOUT);
        client = new DefaultHttpClient(httpParameters);
        HttpPost req = new HttpPost(url);
        req.addHeader("Content-type", "application/json");
        req.addHeader("Accept", "application/json");
        response = client.execute(req);

        if (response != null) {
            return jsonParseString(response.getEntity());
        }
        return null;
    }

	public void stopRequest() {
		if (client != null) {
			client.getConnectionManager().shutdown();
		}
	}

	private JSONObject jsonParseObject(HttpEntity entity) {
		InputStream is;
		StringBuilder sb = null;
		try {
			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			sb = new StringBuilder();

			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();

			return new JSONObject(sb.toString());

		} catch (IllegalStateException e) {
		} catch (IOException e) {
		} catch (JSONException e) {
		}
		return null;
	}

    private String jsonParseString(HttpEntity entity) {
        InputStream is;
        StringBuilder sb = null;
        try {
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            sb = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();

            //return new JSONArray(sb.toString());
            return sb.toString();

        } catch (IllegalStateException e) {
        } catch (IOException e) {
        }
        return null;
    }

	public int getStatusCode() {
		try {
			return response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			return 0;
		}
	}

	public Object objectFromJson(Class<?> c, JSONObject json) {
		Gson gson = new Gson();
		return gson.fromJson(json.toString(), c);
	}

	public ArrayList<?> arrayListFromJsonArray(Type type, JSONArray json) {
		Gson gson = new Gson();
		// Type listType = new TypeToken<List<obj>>(){}.getType();
		return gson.fromJson(json.toString(), type);
	}

	public boolean isConnected() {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = connectivity.getActiveNetworkInfo();
		if (network != null && network.isAvailable() && network.isConnected()) {
			return true;
		}
		// MessageUtils.showErrorMessage(context, R.string.sem_conexao);
		return false;
	}

}
