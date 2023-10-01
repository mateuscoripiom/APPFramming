package com.example.framming;

import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    // Constantes utilizadas pela API
    // URL para a API de Livros do Google.
    private static final String TMDB_URL = "https://api.themoviedb.org/3/movie/";
    // Parametros da string de Busca
    private static final String API_KEY = "api_key";
    private static final String LANGUAGE_S = "language";
    private static final String REGION = "region";
    private static final String TMDB_URL_Q = "https://api.themoviedb.org/3/search/movie";
    private static final String TRENDING = "https://api.themoviedb.org/3/trending/movie";
    private static final String UPCOMING = "https://api.themoviedb.org/3/movie/upcoming";

    private static final OkHttpClient client = new OkHttpClient();
    private static final String QUERY_P = "query";

    private static final String FRAMMING_URL = "https://framming-api.onrender.com/movies";

    static String buscaFilmeString(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeQJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(TMDB_URL_Q).buildUpon()
                    .appendQueryParameter(QUERY_P, queryString)
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .appendQueryParameter(LANGUAGE_S, "pt-BR")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeQJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeQJSONString);
        return filmeQJSONString;
    }

    static String buscaFilmeIDFramming(String movieString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeQJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(FRAMMING_URL).buildUpon()
                    .appendPath(URLEncoder.encode(movieString, "UTF-8"))
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeQJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeQJSONString);
        return filmeQJSONString;
    }

    static String buscaFilme(String movieString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(TMDB_URL).buildUpon()
                    .appendPath(URLEncoder.encode(movieString, "UTF-8"))
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .appendQueryParameter(LANGUAGE_S, "pt-BR")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeJSONString);
        return filmeJSONString;
    }

    static String buscaFilmeEN(String movieString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(TMDB_URL).buildUpon()
                    .appendPath(URLEncoder.encode(movieString, "UTF-8"))
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .appendQueryParameter(LANGUAGE_S, "en-US")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeJSONString);
        return filmeJSONString;
    }

    static String buscaFilmePoster(String movieString, String imageString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(TMDB_URL).buildUpon()
                    .appendPath(URLEncoder.encode(movieString, "UTF-8"))
                    .appendPath(URLEncoder.encode(imageString, "UTF-8"))
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeJSONString);
        return filmeJSONString;
    }

    static String buscaFilmePopulares(String timeString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(TRENDING).buildUpon()
                    .appendPath(URLEncoder.encode(timeString, "UTF-8"))
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .appendQueryParameter(LANGUAGE_S, "pt-BR")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeJSONString);
        return filmeJSONString;
    }

    static String buscaFilmeEmBreve() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(UPCOMING).buildUpon()
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .appendQueryParameter(LANGUAGE_S, "pt-BR")
                    .appendQueryParameter(REGION, "br")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeJSONString);
        return filmeJSONString;
    }

    static String buscaCreditosFilme(String movieString, String creditosString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String filmeQJSONString = null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(TMDB_URL).buildUpon()
                    .appendPath(URLEncoder.encode(movieString, "UTF-8"))
                    .appendPath(URLEncoder.encode(creditosString, "UTF-8"))
                    .appendQueryParameter(API_KEY, "c1266995f1e4319216d8181d18012e4f")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            String linha;
            // Log.d(LOG_TAG, builtURI.toString()); // mostra a uri da api no log pra ver se tá tudo certo
            while ((linha = reader.readLine()) != null) {
                // Adiciona a linha a string.
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
            filmeQJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, filmeQJSONString);
        return filmeQJSONString;
    }

    static String salvaPoster(String userString, String movieString, String posterCaminho) {
        try {
            URL url = new URL("https://framming-api.onrender.com/posters");
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("idUser", userString);
            params.put("idMovie", movieString);
            params.put("posterPath", posterCaminho);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            String urlParameters = postData.toString();
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();

            String result = "";
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                result += line;
            }
            writer.close();
            reader.close();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        }
        return userString;
    }


}
