package com.example.framming;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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

}
