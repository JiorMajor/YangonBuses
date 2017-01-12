package com.theinhtikeaung.yangonbuses.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import com.squareup.okhttp.OkHttpClient;
import com.theinhtikeaung.yangonbuses.models.CacheModel;
import com.theinhtikeaung.yangonbuses.models.Response;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.ConversionException;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import rx.Observable;

/**
 * Abstract class to handle HTTP connection to a web server using Retrofit
 *
 * @author pasca
 */
public abstract class HTTPRetroFitTask {

	public static final String PREF_CACHE = "pref_cache";

	private boolean httpsEnabled = false;
	private boolean isMultipart = false;
	private boolean isCache = false;
	private boolean isJSONRequest = false;
	private boolean isEnableSSLCheck = true;
	private boolean isFollowRedirect = false;
	private boolean isFile = false;
	private boolean needToUpdate = false;
	private boolean updateTheResponse = false;
	private boolean loadedTheCache = false;

	private boolean isUsingImageString = false;
	public void setUsingImageString(boolean usingImageString) {
		isUsingImageString = usingImageString;
	}

	public Response response;

	private boolean isSynchronous = false;
	public void enableSynchronous() {
		isSynchronous = true;
	}

	public boolean isLoadedTheCache() {
		return loadedTheCache;
	}

	private CacheModel cacheModel;

	private boolean isPrototype = false;
	private Context act;
	private String userProfileID;
	private int timeout = 60;

	private int expiryInSeconds;
	private String cacheKey;

	public boolean isNeedToUpdate() {
		return needToUpdate;
	}

	public void setNeedToUpdate(boolean needToUpdate) {
		this.needToUpdate = needToUpdate;
	}

	public CacheModel getCacheModel() {
		return cacheModel;
	}

	public void setCacheModel(CacheModel cacheModel) {
		this.cacheModel = cacheModel;
	}


	public Context getAct() {
		return act;
	}

	public int getExpiryInSeconds() {
		return expiryInSeconds;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setFile(boolean file) {
		isFile = file;
	}

	private TypedFile typeFile;
	public void setTypeFile(TypedFile typeFile) {
		this.typeFile = typeFile;
	}

	private RequestInterceptor requestInterceptor;
	public void setRequestInterceptor(RequestInterceptor requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}

	private OkHttpClient httpClient;
	public void setHttpClient(OkHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	private String url;
	private String method = BaseConstants.GET;
	private Fragment fragment;
	private LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> fileParams = new ArrayList<LinkedHashMap<String, String>>();
	private LinkedHashMap<String, byte[]> bytesParams = new LinkedHashMap<String, byte[]>();
	private HashMap<String, String> mHeaderParams = new HashMap<String, String>();
	private String rawRequestContent;
	private String endpoint;
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public LinkedHashMap<String, String> getParams() {
		return params;
	}

	public void setParams(LinkedHashMap<String, String> params) {
		this.params = params;
	}

	public String getRawRequestContent() {
		return rawRequestContent;
	}

	public void setRawRequestContent(String rawRequestContent) {
		this.rawRequestContent = rawRequestContent;
	}

	public boolean isFollowRedirect() {
		return isFollowRedirect;
	}

	public void setIsFollowRedirect(boolean isFollowRedirect) {
		this.isFollowRedirect = isFollowRedirect;
	}

	public boolean isJSONRequest() {
		return isJSONRequest;
	}

	public void setIsJSONRequest(boolean isJSONRequest) {
		this.isJSONRequest = isJSONRequest;
	}

	public ArrayList<LinkedHashMap<String, String>> getFileParams() {
		return fileParams;
	}

	public void setFileParams(ArrayList<LinkedHashMap<String, String>> fileParams) {
		this.fileParams = fileParams;
	}

	public boolean isMultipart() {
		return isMultipart;
	}

	public void setIsMultipart(boolean isMultipart) {
		this.isMultipart = isMultipart;
	}

	public LinkedHashMap<String, byte[]> getBytesParams() {
		return bytesParams;
	}

	public void setBytesParams(LinkedHashMap<String, byte[]> bytesParams) {
		this.bytesParams = bytesParams;
	}

	public HashMap<String, String> getmHeaderParams() {
		return mHeaderParams;
	}

	public void setmHeaderParams(HashMap<String, String> mHeaderParams) {
		this.mHeaderParams = mHeaderParams;
	}

	protected abstract void onPreExecute();

	protected void onPreExecuteUpdate(){

	}

	protected abstract void onPostExecute(Response response);

	/**
	 * Get the URL
	 *
	 * @return the URL being used for the end point
	 */
	public String getUrl() {
		return url;
	}


	private PinnedHttpsListener pinnedHttpsListener;


	/**
	 * Set the URL to be used to connect to the end point
	 *
	 * @param url , the url to be used
	 */
	public void setUrl(String url, PinnedHttpsListener pinnedHttpsListener) {

		try {
			this.url = url;
			if (url.startsWith("https")) {
				this.httpsEnabled = true;
			}
			if(this.pinnedHttpsListener == null)
			this.pinnedHttpsListener = pinnedHttpsListener;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGetParams(String key, String value) {
		if (key == null || key.trim().length() <= 0 || value == null || value.trim().length() <= 0) { return; }
		this.params.put(key, value);
		this.setMethod(BaseConstants.GET);
	}

	/**
	 * Set the header key value pair
	 *
	 * @param key
	 * @param value
	 */
	public void setHeader(String key, String value) {
		mHeaderParams.put(key, value);
	}

	public String escapeUrlParam(String param) {
		param = param.replace("%", "%25").replace("$", "%24").replace("`", "%60")
				.replace("<", "%3C").replace(">", "%3E").replace("=", "%3D").replace("'", "%27")
				.replace("/", "%2F").replace(":", "%3A").replace("+", "%2B").replace("\"", "%22")
				.replace(" ", "%20").replace("(", "%28").replace(")", "%29").replace("&", "%26")
				.replace("?", "	%3F");
		return param;
	}

	public void setGetParams(String key, int value) {
		String val = String.valueOf(value);
		setGetParams(key, val);
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isHttpsEnabled() {
		return httpsEnabled;
	}

	public void setHttpsEnabled(boolean httpsEnabled) {
		this.httpsEnabled = httpsEnabled;
	}

	public boolean isEnableSSLCheck() {
		return isEnableSSLCheck;
	}

	public void setEnableSSLCheck(boolean isDisableSSLCheck) {
		this.isEnableSSLCheck = isDisableSSLCheck;
	}

	public void setPostParams(String key, String value) {
		if (key == null || key.trim().length() <= 0 || value == null || value.trim().length() <= 0) { return; }
		this.params.put(key, value);
		this.setMethod(BaseConstants.POST);
	}

	public void setPostParams(String key, double value) {
		String d = String.valueOf(value);
		this.setPostParams(key, d);
	}

	public void setPostParams(String key, int value) {
		String d = String.valueOf(value);
		this.setPostParams(key, d);
	}

	public void setImageParams(String key, String absPath) {
		this.setFileParams(key, absPath, BaseConstants.MIME_JPEG);
	}

	public void setCSVParams(String key, String path) {
		this.setFileParams(key, path, BaseConstants.MIME_CSV);
	}

	public void setCache(Context ctx, boolean isCache, String cacheKey, int expiryInSeconds) {
		this.isCache = isCache;
		this.act = act;
		this.cacheKey = cacheKey;
		this.expiryInSeconds = expiryInSeconds;
	}

	public void setFileParams(String key, String path, String mime) {
		if (path.length() <= 0 || key.trim().length() <= 0) { return; }
		this.isMultipart = true;
		String[] q = path.split("/");
		int idx = q.length - 1;
		LinkedHashMap<String, String> file = new LinkedHashMap<String, String>();
		file.put(BaseKeys.KEY, key);
		file.put(BaseKeys.NAME, q[idx]);
		file.put(BaseKeys.FILEPATH, path);
		file.put(BaseKeys.MIME, mime);
		this.fileParams.add(file);
	}

	public void setByteParams(String key, byte[] bytes) {
		if (key.trim().length() <= 0 || bytes == null || bytes.length <= 0) { return; }
		this.isMultipart = true;
		this.bytesParams.put(key, bytes);
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}

	public void execute() {

		if(isSynchronous){
			if (isCache == false) {
				onPreExecute();
				doInBackground();
			}else {

				boolean stillOnCached = false;
				try {
					cacheModel = getCache(act, cacheKey);
					Calendar nowCal = Calendar.getInstance();
					long timeNow = nowCal.getTimeInMillis();
					long timeStamp = cacheModel.getExpiryStamp();
					if (timeNow > timeStamp) {
					} else {
						stillOnCached = true;
					}
				} catch (Exception e) {
				}
				if (stillOnCached) {

					loadCacheData();

					executeParalelPlease(new AsyncTask<Void, Void, Void>() {
						@Override
						protected Void doInBackground(Void... params) {
							try {
//								logThisApi(cacheModel.getResponse(), "200", null);
							} catch (Exception e) {
							}
							return null;
						}

						@Override
						protected void onPostExecute(Void aVoid) {
							super.onPostExecute(aVoid);
							if (needToUpdate) {
								HTTPRetroFitTask.this.onPreExecuteUpdate();
								HTTPRetroFitTask.this.doInBackground(true);
							}
						}
					});

				} else {
					HTTPRetroFitTask.this.onPreExecute();
					HTTPRetroFitTask.this.doInBackground(false);
				}
			}
		}else {

			try {
				if (isCache == false) {
					if (act != null)
						if (!Helper.isNetworkAvailable(act)) {
							onPostExecute(new Response(HttpURLConnection.HTTP_NOT_ACCEPTABLE, ""));
							return;
						}

					onPreExecute();
					doInBackground();
				} else {
					executeParalelPlease(new AsyncTask<Void, Void, Void>() {

						boolean stillOnCached = false;

						@Override
						protected Void doInBackground(Void... voids) {
							try {
								cacheModel = getCache(act, cacheKey);
								Calendar nowCal = Calendar.getInstance();
								long timeNow = nowCal.getTimeInMillis();
								long timeStamp = cacheModel.getExpiryStamp();
								if (timeNow > timeStamp) {
								} else {
									stillOnCached = true;
								}
							} catch (Exception e) {
							}
							return null;
						}

						@Override
						protected void onPostExecute(Void aVoid) {
							super.onPostExecute(aVoid);
							if (stillOnCached) {

								loadCacheData();

								executeParalelPlease(new AsyncTask<Void, Void, Void>() {
									@Override
									protected Void doInBackground(Void... params) {
										try {
//											logThisApi(cacheModel.getResponse(), "200", null);
										} catch (Exception e) {
										}
										return null;
									}

									@Override
									protected void onPostExecute(Void aVoid) {
										super.onPostExecute(aVoid);
										if (needToUpdate) {
											HTTPRetroFitTask.this.onPreExecuteUpdate();
											HTTPRetroFitTask.this.doInBackground(true);
										}
									}
								});

							} else {
								HTTPRetroFitTask.this.onPreExecute();
								HTTPRetroFitTask.this.doInBackground(false);
							}
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean loadCacheData(){
		try {
			if(cacheModel == null) {
				cacheModel = getCache(act, cacheKey);
				Calendar nowCal = Calendar.getInstance();
				long timeNow = nowCal.getTimeInMillis();
				long timeStamp = cacheModel.getExpiryStamp();
				if (timeNow > timeStamp) {
					return false;
				}
			}
		} catch (Exception e) {}

		try {
			loadedTheCache = true;
			updateTheResponse = false;
			Response res = new Response(666, cacheModel.getResponse());
			res.setUpdateResponse(updateTheResponse);
			HTTPRetroFitTask.this.onPostExecute(res);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void cleanCacheUnused(final Context ctx){
		executeParalelPlease(new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				try {
					Map<String, ?> allEntries = ctx.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).getAll();
					for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

                        try {
                            CacheModel cacheModel = getCache(ctx, entry.getKey());
                            Calendar nowCal = Calendar.getInstance();
                            long timeNow =  nowCal.getTimeInMillis();
                            long timeStamp = cacheModel.getExpiryStamp();
                            if(timeNow>timeStamp){
                                SharedPreferences.Editor editor = ctx.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit();
                                editor.remove(entry.getKey());
								editor.commit();
                            }
                        } catch (Exception e) {}

                    }
				} catch (Exception e) {}
				return null;
			}
		});
	}

	public void clearCache(final Context ctx, final ArrayList<String> ignoreKeys){
//		try {
//			ctx.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit().clear().commit();
//		} catch (Exception e) {}

		executeParalelPlease(new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				try {
					Map<String, ?> allEntries = ctx.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).getAll();

					for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

						try {
							boolean notFound = true;
							for(String ignorekey : ignoreKeys){
								if(entry.getKey().equalsIgnoreCase(ignorekey)){
									notFound = false;
									break;
								}
							}

							if(notFound){
								SharedPreferences.Editor editor = ctx.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit();
								editor.remove(entry.getKey());
								editor.commit();
							}
						} catch (Exception e) {}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}
		});

	}

	/**
	 * Disables the SSL certificate checking for new instances of {@link HttpsURLConnection} This
	 * has been created to aid testing on a local box, not for use on production.
	 */
	private static void disableSSLCertificateChecking() {
		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {

					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					@Override
					public void checkClientTrusted(X509Certificate[] arg0, String arg1)
							throws CertificateException {
						// Not implemented
					}

					@Override
					public void checkServerTrusted(X509Certificate[] arg0, String arg1)
							throws CertificateException {
						// Not implemented
					}
				}
		};

		try {
			SSLContext sc = SSLContext.getInstance("TLS");

			sc.init(null, trustAllCerts, new java.security.SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		catch (KeyManagementException e) {
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String[] getHeaders(HttpURLConnection con, String header) {
		List<String> values = new ArrayList<String>();
		int idx = (con.getHeaderFieldKey(0) == null) ? 1 : 0;
		while (true) {
			String key = con.getHeaderFieldKey(idx);
			if (key == null)
				break;
			if (header.equalsIgnoreCase(key))
				values.add(con.getHeaderField(idx));
			++idx;
		}
		return values.toArray(new String[values.size()]);
	}

	public static void executeParalelPlease(AsyncTask<Void,Void,Void> task){
		try {
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) {
				task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
			else {
				task.execute();
			}
		} catch (Exception e) {}
	}

	public static class StringConverter implements retrofit.converter.Converter {

		@Override
		public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
			String text = null;
			try {
				text = fromStream(typedInput.in());
			} catch (IOException ignored) {/*NOP*/ }

			return text;
		}

		@Override
		public TypedOutput toBody(Object o) {
			return null;
		}

		public static String fromStream(InputStream in) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder out = new StringBuilder();
			String newLine = System.getProperty("line.separator");
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
				out.append(newLine);
			}
			return out.toString();
		}
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setIsPrototype(boolean isPrototype) {
		this.isPrototype = isPrototype;
	}

	public void setUserProfileID(String userProfileID) {
		this.userProfileID = userProfileID;
	}

	public void setAct(Context act) {
		this.act = act;
	}

//	public void logThisApi(String content, String statuscode, String headers){
//		try {
//			if(act!=null){
//                BeanLogAPI beanApi = new BeanLogAPI();
//
//				String Title = "";
//
//                try {
//                    if(!BaseHelper.isEmpty(this.getUrl()))
//                    beanApi.setUrl(this.getUrl());
//                } catch (Exception e) {}
//
//				try {
//					if(!BaseHelper.isEmpty(this.getClass().toString()))
//						beanApi.setShortenClassName(this.getClass());
//				} catch (Exception e) {}
//
//				if(isCache) {
//					if(updateTheResponse)
//						Title = "API Update";
//					else
//						Title = "Cache";
//				}else
//					Title = "API";
//
//				try {
//					String cacheDesc = "";
//
//					if(isCache){
//						if(updateTheResponse) {
//							cacheDesc += "Loaded From: API\n";
//						}else{
//							cacheDesc += "Loaded From: Cache\n";
//							try {
//								cacheDesc += "Cache expired on: " + DateFormat.format(BaseConstants.DATE_EDMYHMS,
//										cacheModel.getExpiryStamp()).toString() + "\n";
//							} catch (Exception e) {
//								cacheDesc = "";
//								cacheDesc += "Loaded From: API\n";
//							}
//						}
//					}else{
//						cacheDesc += "Loaded From: API\n";
//					}
//
//					try {
//						if(!BaseHelper.isEmpty(headers)) {
//							beanApi.setHeader(headers);
//							cacheDesc = "";
//							cacheDesc += "Loaded From: API\n";
//							Title = "API";
//							if(updateTheResponse) Title = "API Update";
//						}else{
//							cacheDesc = "";
//							cacheDesc += "Loaded From: Cache\n";
//							Title = "Cache";
//							try {
//								cacheDesc += "Cache expired on: " + DateFormat.format(BaseConstants.DATE_EDMYHMS,
//										cacheModel.getExpiryStamp()).toString() + "\n";
//							} catch (Exception e) {}
//						}
//					} catch (Exception e) {}
//
//					if(!Helper.isEmpty(this.getMethod().toString()))
//						beanApi.setMethod(this.getMethod().toString() + "\n" + cacheDesc);
//				} catch (Exception e) {}
//
//
//                try {
//                    if(content!=null)
//                    beanApi.setContent(content);
//                } catch (Exception e) {}
//
//                try {
//                    if(!BaseHelper.isEmpty(getParams().toString()))
//                    beanApi.setParams(getParams().toString());
//                } catch (Exception e) {}
//
//                try {
//                    if(!BaseHelper.isEmpty(statuscode))
//                    beanApi.setStatuscode(statuscode);
//                } catch (Exception e) {}
//
//				try {
//					if(!BaseHelper.isEmpty(getFileParams().toString()))
//						beanApi.setFileparam(getFileParams().toString());
//				} catch (Exception e) {}
//
//				try {
//					if(!BaseHelper.isEmpty(getBytesParams().toString()))
//						beanApi.setByteparam(getBytesParams().toString());
//				} catch (Exception e) {}
//
//				Helper.logThisAPI(act, beanApi, Title);
//            }
//		} catch (Exception e) {}
//	}

	protected void doInBackground() {
		doInBackground(false);
	}

	protected void doInBackground(boolean update) {

		updateTheResponse = update;

		if(httpClient == null){

			SSLSocketFactory NoSSLv3Factory = null;

			if(httpsEnabled) {
				try {
					URL urlx = new URL(url);
					NoSSLv3Factory = new NoSSLv3SocketFactory(urlx);
				} catch (Exception e) {}
			}

			try {

				httpClient = pinnedHttpsListener.onGeneratePinned();
				if(httpsEnabled)
					httpClient.setSslSocketFactory(NoSSLv3Factory);
				if(httpClient == null){
					httpClient = new OkHttpClient();
					if(httpsEnabled)
						httpClient.setSslSocketFactory(NoSSLv3Factory);
				}
			} catch (Exception e) {
				httpClient = new OkHttpClient();
				try {
					if(httpsEnabled)
						httpClient.setSslSocketFactory(NoSSLv3Factory);
				} catch (Exception e1) {}
			}

		}

		if(isPrototype){

			new AsyncTask<Void, Void, Void>() {
				@Override
				protected Void doInBackground(Void... params) {
					try {
						Thread.sleep(1500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(Void aVoid) {
					HTTPRetroFitTask.this.onPostExecute(null);
				}
			}.execute();

			return;
		}



		if (this.method.equalsIgnoreCase(BaseConstants.POST)) {

			try {
				if (isJSONRequest) {

					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(url)
//							.setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(HTTPRetroFitTask.class.getSimpleName()))
							.setConverter(new StringConverter())
							.setClient(new OkClient(httpClient))
							.build();


					RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);
					TypedJsonString in = new TypedJsonString(rawRequestContent);
					service.postJSONResult(endpoint, params, in, callbackJSON);


				} else {

					RestAdapter restAdapter;
					httpClient.setConnectTimeout(timeout, TimeUnit.SECONDS);
					httpClient.setReadTimeout(timeout, TimeUnit.SECONDS);
					httpClient.setWriteTimeout(timeout, TimeUnit.SECONDS);

					if(requestInterceptor == null){
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
//								 .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(HTTPRetroFitTask.class.getSimpleName()))
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
								.build();

					}else{
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setRequestInterceptor(requestInterceptor)
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
								.build();

					}

					RetrofitAPIService	service = restAdapter.create(RetrofitAPIService.class);

					if(isMultipart){
						if(isFile)
							service.postFileResult(typeFile, params, callbackJSON);
						else {

							if(isUsingImageString){
								service.postResultImage(typeFile, params, callbackJSON);
							}else{
								service.postResult(typeFile, params, callbackJSON);
							}

						}
//						service.postResult(typeFile, userProfileID, callbackJSON);
					}else {
						if(isSynchronous) {
							service.postSynchJSONResult("", params, callbackJSONSynchronous);
//							String s = service.postSynchJSONResult(url, params);
//							response = new Response(200, s);
						}else
							service.postResult(params, callbackJSON);
					}

				}
			} catch (Exception e) {}

		} else {

			try {
				if (isJSONRequest) {

					RestAdapter restAdapter = new RestAdapter.Builder()
							.setEndpoint(url)
							.setConverter(new StringConverter())
							.setClient(new OkClient(httpClient))
							.build();


					RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);
					TypedJsonString in = new TypedJsonString(rawRequestContent);
					service.getJSONResult(endpoint, params, in, callbackJSON);

				} else {

					RestAdapter restAdapter;
					httpClient.setConnectTimeout(timeout, TimeUnit.SECONDS);
					httpClient.setReadTimeout(timeout, TimeUnit.SECONDS);
					httpClient.setWriteTimeout(timeout, TimeUnit.SECONDS);

					if(requestInterceptor==null){
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
//								.setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(HTTPRetroFitTask.class.getSimpleName()))
								.build();
					}else{
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setRequestInterceptor(requestInterceptor)
								.setConverter(new StringConverter())
//								.setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(HTTPRetroFitTask.class.getSimpleName()))
								.setClient(new OkClient(httpClient))
								.build();
					}

					RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);

					if(isSynchronous){
						service.getSynchJSONResult("", params, callbackJSONSynchronous);
//						String s = service.getSynchJSONResult(url, params, callbackJSONSynchronous);
//						response = new Response(200, s);
					}else
						service.getResult(params, callbackJSON);


				}
			} catch (Exception e) {}

		}
	}


	public Observable<String> getAPIServiceRetrofitObjectForMultipleRequestPurposes() {

		if(httpClient == null){

			SSLSocketFactory NoSSLv3Factory = null;

			if(httpsEnabled) {
				try {
					URL urlx = new URL(url);
					NoSSLv3Factory = new NoSSLv3SocketFactory(urlx);
				} catch (Exception e) {}
			}

			try {

				httpClient = pinnedHttpsListener.onGeneratePinned();
				if(httpsEnabled)
					httpClient.setSslSocketFactory(NoSSLv3Factory);
				if(httpClient == null){
					httpClient = new OkHttpClient();
					if(httpsEnabled)
						httpClient.setSslSocketFactory(NoSSLv3Factory);
				}
			} catch (Exception e) {
				httpClient = new OkHttpClient();
				try {
					if(httpsEnabled)
						httpClient.setSslSocketFactory(NoSSLv3Factory);
				} catch (Exception e1) {}
			}

		}

		if (this.method.equalsIgnoreCase(BaseConstants.POST)) {

			try {
					RestAdapter restAdapter;
					httpClient.setConnectTimeout(timeout, TimeUnit.SECONDS);
					httpClient.setReadTimeout(timeout, TimeUnit.SECONDS);
					httpClient.setWriteTimeout(timeout, TimeUnit.SECONDS);

					if(requestInterceptor == null){
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
								.build();

					}else{
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setRequestInterceptor(requestInterceptor)
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
								.build();

					}

					RetrofitAPIService	service = restAdapter.create(RetrofitAPIService.class);

					if(isMultipart){
						if(isFile)
							return service.postFileResult(typeFile, params);
						else
							return service.postResult(typeFile, params);
					}else {

						if(isCache){
							try {
								boolean stillOnCached = false;
								Calendar nowCal = Calendar.getInstance();
								long timeNow =  nowCal.getTimeInMillis();
								long timeStamp = cacheModel.getExpiryStamp();
								if(timeNow>timeStamp){
								}else{
									stillOnCached = true;
								}

								if(stillOnCached) {
									return Observable.just(cacheModel.getResponse());
								}

							} catch (Exception e) {}
						}

						return service.postResult(params);
					}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
					RestAdapter restAdapter;

					if(requestInterceptor==null){
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
								.build();
					}else{
						restAdapter = new RestAdapter.Builder()
								.setEndpoint(url)
								.setRequestInterceptor(requestInterceptor)
								.setConverter(new StringConverter())
								.setClient(new OkClient(httpClient))
								.build();
					}

					RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);

					if(isCache){
						try {
							boolean stillOnCached = false;
							Calendar nowCal = Calendar.getInstance();
							long timeNow =  nowCal.getTimeInMillis();
							long timeStamp = cacheModel.getExpiryStamp();
							if(timeNow>timeStamp){
							}else{
								stillOnCached = true;
							}

							if(stillOnCached) {
								return Observable.just(cacheModel.getResponse());
							}

						} catch (Exception e) {}
					}

						return service.getResult(params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static Response checkCacheObservable(final HTTPRetroFitTask task, final String response, CacheModel cacheModel){
		try {
			if(task.isCache) {
				boolean needSaveCache = false;
				try {
					Calendar nowCal = Calendar.getInstance();
					long timeNow = nowCal.getTimeInMillis();
					long timeStamp = cacheModel.getExpiryStamp();
					if(timeNow>timeStamp){
						needSaveCache = true;
					}
				} catch (Exception e) {
					needSaveCache = true;
				}

				if(needSaveCache) {
					cacheModel = new CacheModel();
					cacheModel.setResponse(response);
					Calendar savedCal = Calendar.getInstance();
					savedCal.add(Calendar.SECOND, task.getExpiryInSeconds());
					cacheModel.setExpiryStamp(savedCal.getTimeInMillis());
					task.saveCache(task.getAct(), cacheModel, task.getCacheKey());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(task!=null){
			executeParalelPlease(new AsyncTask<Void, Void, Void>() {
				@Override
				protected Void doInBackground(Void... params) {
//					try {
//						task.logThisApi(response, "200", null);
//					} catch (Exception e) {}
					return null;
				}
			});
			return HTTPRetroFitTask.getResponseObject(response);
		}

		return null;
	}

//	public static Response getSynchronous(String url, String endpoint, LinkedHashMap<String, String> params){
//
//		try {
//
//			final OkHttpClient okHttpClient = new OkHttpClient();
//			okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
//			okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
//			okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
//
//			RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint(url)
//                    .setConverter(new StringConverter())
//					.setClient(new OkClient(okHttpClient))
//					.build();
//
//			RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);
//			String s = service.getSynchJSONResult(endpoint, params);
//			return new Response(200, s);
//		} catch (Exception e) {
//			return new Response(408, "Unknown Error");
//		}
//	}
//
//	public static Response postSynchronous(String url, String endpoint, LinkedHashMap<String, String> params){
//
//		try {
//
////			final OkHttpClient okHttpClient = new OkHttpClient();
////			okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
////			okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
////			okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
//
//
//			RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint(url)
//                    .setConverter(new StringConverter())
////					.setClient(new OkClient(okHttpClient))
//                    .build();
//
//			RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);
//			String s = service.postSynchJSONResult(endpoint, params);
//			return new Response(200, s);
//		} catch (Exception e) {
//			return new Response(408, "Unknown Error");
//		}
//
//	}

//	public static Response getJSONSynchronous(String url, String endpoint,
//											  String json, LinkedHashMap<String, String> params){
//
//		try {
//
//			final OkHttpClient okHttpClient = new OkHttpClient();
//			okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
//			okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
//			okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
//
//			RestAdapter restAdapter = new RestAdapter.Builder()
//					.setEndpoint(url)
//					.setConverter(new StringConverter())
//					.setClient(new OkClient(okHttpClient))
//					.build();
//
//			RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);
//			TypedJsonString in = new TypedJsonString(json);
//			String s = service.getSynchJSONResult(endpoint, params, in);
//			return new Response(200, s);
//		} catch (Exception e) {
//			return new Response(408, "Unknown Error");
//		}
//	}
//
//	public static Response postJSONSynchronous(String url, String endpoint,
//											   String json, LinkedHashMap<String, String> params){
//
//		try {
////			final OkHttpClient okHttpClient = new OkHttpClient();
////			okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
////			okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
////			okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
//
//			RestAdapter restAdapter = new RestAdapter.Builder()
//					.setEndpoint(url)
//					.setConverter(new StringConverter())
////					.setClient(new OkClient(okHttpClient))
//					.build();
//
//			RetrofitAPIService service = restAdapter.create(RetrofitAPIService.class);
//			TypedJsonString in = new TypedJsonString(json);
//			String s = service.postSynchJSONResult(endpoint, params, in);
//			return new Response(200, s);
//		} catch (Exception e) {
//			return new Response(408, "Unknown Error");
//		}
//
//	}

	public void saveCache(final Context act, final CacheModel cacheObj, final String key){
		executeParalelPlease(new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				try {
					SharedPreferences.Editor editor = act.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).edit();

					try {
                        editor.putString(key, ObjectSerializer.serialize(cacheObj));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
					editor.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public CacheModel getCache(Context act, String key) {
		try {
			String test = act.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE).getString(key, null);
			return (CacheModel) ObjectSerializer.deserialize(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isUpdateResponse(HTTPRetroFitTask task, Response res){
		try {
			if(res.isUpdateResponse()){
                return true;
            }else{
				if(task.isLoadedTheCache()){
					return false;
				}
				return false;
            }
		} catch (Exception e) {
			try {
				if(task.isCache)
                    return true;
                else
                    return false;
			} catch (Exception e1) {
				return false;
			}
		}
	}

	private retrofit.client.Response responseX;
	private JSONObject jsonObjectX;
	private String sX;
	private RetrofitError errorX;

	public static Response getResponseObject(String s){
		try {
			JSONObject jsonObject = new JSONObject(s);
			Response res = new Response(999, jsonObject.toString());
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	private Callback<String> callbackJSON = new Callback<String>() {

		@Override
		public void success(final String s, final retrofit.client.Response response) {
			executeParalelPlease(new AsyncTask<Void, Void, Void>() {
				Response res;
				JSONObject jsonObject;
				boolean isFailed = false;
				@Override
				protected Void doInBackground(Void... voids) {
					try {
						sX = s;
						responseX = response;
						jsonObject = new JSONObject(s);
						res = new Response(response.getStatus(), jsonObject.toString());

						if(isCache){
							if(response.getStatus() == 666)updateTheResponse = false;
							res.setUpdateResponse(updateTheResponse);
							CacheModel cacheModel = new CacheModel();
							cacheModel.setResponse(jsonObject.toString());
							Calendar savedCal = Calendar.getInstance();
							savedCal.add(Calendar.SECOND, expiryInSeconds);
							cacheModel.setExpiryStamp(savedCal.getTimeInMillis());
							saveCache(act, cacheModel, cacheKey);
						}
					} catch (Exception e) {
						isFailed = true;
					}
					return null;
				}

				@Override
				protected void onPostExecute(Void aVoid) {
					super.onPostExecute(aVoid);

					if(isFailed){

						HTTPRetroFitTask.this.onPostExecute(null);

						responseX = response;
						executeParalelPlease(new AsyncTask<Void, Void, Void>() {

							@Override
							protected Void doInBackground(Void... params) {
//								try {
//									if(responseX!=null)
////										logThisApi(sX, String.valueOf(responseX.getStatus()),
//												responseX.getHeaders().toString());
//									else
////										logThisApi(sX, "N/A", responseX.getHeaders().toString());
//
//								} catch (Exception e) {}
								return null;
							}
						});

					}else{
						HTTPRetroFitTask.this.onPostExecute(res);

						jsonObjectX = jsonObject;
						executeParalelPlease(new AsyncTask<Void, Void, Void>() {

							@Override
							protected Void doInBackground(Void... params) {
//								try {
//									if (responseX != null)
////										logThisApi(jsonObjectX.toString(),
//												String.valueOf(responseX.getStatus()),
//												responseX.getHeaders().toString());
//									else
//										logThisApi(jsonObjectX.toString(),
//												"N/A",
//												responseX.getHeaders().toString());
//								} catch (Exception e) {}
								return null;
							}
						});
					}





				}
			});

		}


		@Override
		public void failure(RetrofitError error) {
			try {

				RetrofitError retrofitError;
				if (error instanceof RetrofitError) {
					retrofitError = ((RetrofitError) error);
					if (retrofitError.getKind() == RetrofitError.Kind.NETWORK) {
						onPostExecute(new Response(HttpURLConnection.HTTP_NOT_ACCEPTABLE, null));
						return;
					}
				}

				Response res = new Response(error.getResponse().getStatus(), error.getLocalizedMessage());
				String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());

				sX = json;
				errorX = error;

				onPostExecute(new Response(error.getResponse().getStatus(), json));

				executeParalelPlease(new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
//						try {
//							if(errorX!=null)
//								logThisApi(sX, String.valueOf(errorX.getResponse().getStatus()),
//										errorX.getResponse().getHeaders().toString());
//							else
//								logThisApi(errorX.getBody().toString(), "N/A", errorX.getResponse().getHeaders().toString());
//
//						} catch (Exception e) {}
						return null;
					}
				});

			} catch (Exception e) {
				onPostExecute(null);
				errorX = error;
				executeParalelPlease(new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
						try {
//							logThisApi(errorX.getBody().toString(), "N/A", errorX.getResponse().getHeaders().toString());
						} catch (Exception e) {}
						return null;
					}
				});

			}
		}
	};



//	public void checkResponseSynchronous(final String s) {
//
//		Response res;
//		JSONObject jsonObject;
//		boolean isFailed = false;
//		try {
//			sX = s;
//			jsonObject = new JSONObject(s);
//			res = new Response(200, jsonObject.toString());
//
//			if(isCache){
//				if(response.getStatus() == 666)updateTheResponse = false;
//				res.setUpdateResponse(updateTheResponse);
//				CacheModel cacheModel = new CacheModel();
//				cacheModel.setResponse(jsonObject.toString());
//				Calendar savedCal = Calendar.getInstance();
//				savedCal.add(Calendar.SECOND, expiryInSeconds);
//				cacheModel.setExpiryStamp(savedCal.getTimeInMillis());
//				saveCache(act, cacheModel, cacheKey);
//			}
//		} catch (Exception e) {
//			isFailed = true;
//		}
//		if(isFailed){
//
//			HTTPRetroFitTask.this.onPostExecute(null);
//
//			responseX = response;
//			executeParalelPlease(new AsyncTask<Void, Void, Void>() {
//
//				@Override
//				protected Void doInBackground(Void... params) {
//					try {
//						if(responseX!=null)
//							logThisApi(sX, String.valueOf(responseX.getStatus()),
//									responseX.getHeaders().toString());
//						else
//							logThisApi(sX, "N/A", responseX.getHeaders().toString());
//
//					} catch (Exception e) {}
//					return null;
//				}
//			});
//
//		}else{
//			HTTPRetroFitTask.this.onPostExecute(res);
//
//			jsonObjectX = jsonObject;
//			executeParalelPlease(new AsyncTask<Void, Void, Void>() {
//
//				@Override
//				protected Void doInBackground(Void... params) {
//					try {
//						if (responseX != null)
//							logThisApi(jsonObjectX.toString(),
//									String.valueOf(responseX.getStatus()),
//									responseX.getHeaders().toString());
//						else
//							logThisApi(jsonObjectX.toString(),
//									"N/A",
//									responseX.getHeaders().toString());
//					} catch (Exception e) {}
//					return null;
//				}
//			});
//		}
//
//	}


	private Callback<String> callbackJSONSynchronous = new Callback<String>() {

		@Override
		public void success(String s, retrofit.client.Response response) {
			Response res = null;
			JSONObject jsonObject = null;
			boolean isFailed = false;

			try {
				sX = s;
				responseX = response;
				jsonObject = new JSONObject(s);
				res = new Response(response.getStatus(), jsonObject.toString());

				if(isCache){
					if(response.getStatus() == 666)updateTheResponse = false;
					res.setUpdateResponse(updateTheResponse);
					CacheModel cacheModel = new CacheModel();
					cacheModel.setResponse(jsonObject.toString());
					Calendar savedCal = Calendar.getInstance();
					savedCal.add(Calendar.SECOND, expiryInSeconds);
					cacheModel.setExpiryStamp(savedCal.getTimeInMillis());
					saveCache(act, cacheModel, cacheKey);
				}
			} catch (Exception e) {
				isFailed = true;
			}


			if(isFailed){

				HTTPRetroFitTask.this.onPostExecute(null);

				responseX = response;
				executeParalelPlease(new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
//						try {
//							if(responseX!=null)
//								logThisApi(sX, String.valueOf(responseX.getStatus()),
//										responseX.getHeaders().toString());
//							else
//								logThisApi(sX, "N/A", responseX.getHeaders().toString());
//
//						} catch (Exception e) {}
						return null;
					}
				});

			}else{
				HTTPRetroFitTask.this.onPostExecute(res);

				jsonObjectX = jsonObject;
				executeParalelPlease(new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
//						try {
//							if (responseX != null)
//								logThisApi(jsonObjectX.toString(),
//										String.valueOf(responseX.getStatus()),
//										responseX.getHeaders().toString());
//							else
//								logThisApi(jsonObjectX.toString(),
//										"N/A",
//										responseX.getHeaders().toString());
//						} catch (Exception e) {}
						return null;
					}
				});
			}



		}


		@Override
		public void failure(RetrofitError error) {
			try {

				RetrofitError retrofitError;
				if (error instanceof RetrofitError) {
					retrofitError = ((RetrofitError) error);
					if (retrofitError.getKind() == RetrofitError.Kind.NETWORK) {
						onPostExecute(new Response(HttpURLConnection.HTTP_NOT_ACCEPTABLE, null));
						return;
					}
				}

				Response res = new Response(error.getResponse().getStatus(), error.getLocalizedMessage());
				String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());

				sX = json;
				errorX = error;

				onPostExecute(new Response(error.getResponse().getStatus(), json));

				executeParalelPlease(new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
//						try {
//							if(errorX!=null)
//								logThisApi(sX, String.valueOf(errorX.getResponse().getStatus()),
//										errorX.getResponse().getHeaders().toString());
//							else
//								logThisApi(errorX.getBody().toString(), "N/A", errorX.getResponse().getHeaders().toString());
//
//						} catch (Exception e) {}
						return null;
					}
				});

			} catch (Exception e) {
				onPostExecute(null);
				errorX = error;
				executeParalelPlease(new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
//						try {
//							logThisApi(errorX.getBody().toString(), "N/A", errorX.getResponse().getHeaders().toString());
//						} catch (Exception e) {}
						return null;
					}
				});

			}
		}
	};

}
