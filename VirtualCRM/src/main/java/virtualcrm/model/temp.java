
    /*public class Request {

        public static String sendGetRequest(String endpoint, String token, String query) {
            String url = endpoint + "?q=" + query;

            try {
                URL urlObj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Authorization", token);
                connection.setRequestProperty("Accept", "application/json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                return response.toString();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final String token="Bearer 00D07000000YPGQ!AR4AQL.4g9_6qsJw1pfW40cr.dKC.kOmIOqnETQYX0Mvrop2ur4kttCVf3ulroaPk9wQzFOjkFmj0G5ifzUbtveTXWwUDJBK";
    private final String endpoint="https://univangers-dev-ed.develop.my.salesforce.com/services/data/v45.0/query/";*/