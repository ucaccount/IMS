
    public static void main(String[] args) 
     public static void main(String[] args) {
        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD + SECURITY_TOKEN);
        config.setAuthEndpoint(AUTH_ENDPOINT);
        config.setServiceEndpoint(AUTH_ENDPOINT);

        try {
            PartnerConnection connection = Connector.newConnection(config);
            QueryResult result = connection.query("SELECT Id, Name, Email FROM Contact LIMIT 10");
            SObject[] records = result.getRecords();
            for (SObject record : records) {
                System.out.println(record.getField("Name"));
            }
            connection.logout();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        String username = "your-username";
        String password = "your-password";
        String securityToken = "your-security-token";

        ConnectorConfig config = new ConnectorConfig();

        config.setUsername(username);
        config.setPassword(password + securityToken);

        EnterpriseConnection connection = new EnterpriseConnection(config);

        String query = "SELECT Name, Industry FROM Account LIMIT 10";
        QueryResult result = connection.query(query);

        while (!result.isDone()) {
            Account[] records = result.getRecords();
            for (Account record : records) {
                System.out.println(record.getName() + " - " + record.getIndustry());
            }
            result = connection.queryMore(result.getQueryLocator());
        }

        connection.logout();
    }
    
    {
        try {
            // Get an access token to authenticate requests to the Graph API
            AuthenticationContext authContext = new AuthenticationContext(AUTHORITY, false, null);
            ClientCredential clientCred = new ClientCredential(CLIENT_ID, CLIENT_SECRET);
            Future<AuthenticationResult> authResult = authContext.acquireToken(GRAPH_API_ENDPOINT, clientCred, null);
            String accessToken = authResult.get().getAccessToken();

            // Get user information from the Graph API
            GraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(request -> request.addHeader("Authorization", "Bearer " + accessToken)).buildClient();
            User user = graphClient.me().buildRequest().get();
            System.out.println("User display name: " + user.displayName);

            // Get a list of events from the user's calendar
            Calendar calendar = graphClient.me().calendar().buildRequest().get();
            List<Event> events = graphClient.me().calendar().events().buildRequest().get().getCurrentPage();
            for (Event event : events) {
                System.out.println("Event subject: " + event.subject);
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }




    public static asada(String[] args) {
        try {
            AuthenticationContext authContext = new AuthenticationContext("https://login.microsoftonline.com/common", false, null);
            AuthenticationResult authResult = authContext.acquireToken("https://analysis.windows.net/powerbi/api", clientId, username, password, null).get();
            PowerBIClient client = new PowerBIClient(authResult.getAccessToken());
            
            EmbedToken token = client.reports().generateToken("<reportId>").execute();
            
            Dashboards dashboards = client.dashboards().getDashboards(workspaceId).execute();
            for (Dashboard dashboard : dashboards.getValue()) {
                System.out.println(dashboard.getDisplayName());
            }
        } catch (PowerBIException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

         using (SqlConnection conn = new SqlConnection("YourConnectionString"))
        {
            using (SqlCommand cmd = new SqlCommand("YourStoredProcedureName", conn))
            {
                // Set the command type as stored procedure
                cmd.CommandType = CommandType.StoredProcedure;

                // Add parameters to the stored procedure (if required)
                cmd.Parameters.AddWithValue("@parameter1", "value1");
                cmd.Parameters.AddWithValue("@parameter2", "value2");

                conn.Open();

                // Execute the stored procedure
                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        Console.WriteLine("{0} - {1}", reader.GetString(0), reader.GetString(1));
                    }
                }
            }
        }
    }

        public static void main(String[] args) {

    try {
      String siteUrl = "https://<your-tenant>.sharepoint.com/sites/<your-site>";
      String username = "<your-username>";
      String password = "<your-password>";

      SharePointOnlineCredentials credentials = new SharePointOnlineCredentials(username, password);
      ClientContext context = new ClientContext(siteUrl);
      context.setCredentials(credentials);

      Web web = context.getWeb();
      context.load(web);
      context.executeQuery();

      System.out.println("Web title: " + web.getTitle());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) throws ConnectionException {
        // Set up authentication
        String username = "your_salesforce_username";
        String password = "your_salesforce_password";
        String securityToken = "your_salesforce_security_token";

        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(password + securityToken);

        // Set up connection
        EnterpriseConnection connection = Connector.newConnection(config);

        // Fetch a list of accounts
        String soqlQuery = "SELECT Id, Name FROM Account";
        List<SObject> records = Arrays.asList(connection.query(soqlQuery).getRecords());
        for (SObject record : records) {
            Account account = (Account) record;
            System.out.println(account.getName());
        }


        // Close connection
        connection.logout();
    }




    public static void main(String[] args) {

        // Specify the Dynamics 365 instance URL
        String orgUrl = "https://<organization>.crm.dynamics.com";

        // Specify the Azure AD application client ID and client secret
        String clientId = "<client_id>";
        String clientSecret = "<client_secret>";

        // Specify the Dynamics 365 organization username and password
        String username = "<username>";
        String password = "<password>";

        try {
            // Authenticate with Azure AD to get an access token
            AuthenticationResult authResult = authenticateWithAzureAD(clientId, clientSecret);

            // Create an authentication credentials object with the access token
            AuthenticationCredentials authCreds = new AuthenticationCredentials();
            authCreds.setAuthenticationType(AuthenticationType.OAuth);
            authCreds.setOAuthToken(authResult.getAccessToken());

            // Create a CrmConnection object with the organization URL and authentication credentials
            CrmConnection connection = new CrmConnection(new URI(orgUrl), authCreds);

            // Create an OrganizationServiceProxy object with the CrmConnection object
            OrganizationServiceProxy orgService = new OrganizationServiceProxy(connection);

            // Retrieve a single account record by ID
            RetrieveRequest retrieveRequest = new RetrieveRequest();
            retrieveRequest.setTarget(new com.microsoft.crm.sdk.Messages.EntityReference("account", new java.util.UUID("<account_id>")));
            RetrieveResponse retrieveResponse = orgService.execute(retrieveRequest);
            System.out.println(retrieveResponse.getEntity().getAttributeValue("name"));

            // Retrieve a list of account records using a QueryExpression
            RetrieveMultipleRequest retrieveMultipleRequest = new RetrieveMultipleRequest();
            retrieveMultipleRequest.setQuery(new com.microsoft.crm.sdk.Query.QueryExpression("account"));
            RetrieveMultipleResponse retrieveMultipleResponse = orgService.execute(retrieveMultipleRequest);
            for (com.microsoft.crm.sdk.Data.Entity account : retrieveMultipleResponse.getEntityCollection().getEntities()) {
                System.out.println(account.getAttributeValue("name"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static AuthenticationResult authenticateWithAzureAD(String clientId, String clientSecret) throws Exception {
        String authority = "https://login.microsoftonline.com/common";
        String resource = "https://<organization>.crm.dynamics.com";
        AuthenticationContext authContext = new AuthenticationContext(authority, false, null);
        ClientCredential clientCred = new ClientCredential(clientId, clientSecret);
        return authContext.acquireToken(resource, clientCred, null).get();
    }


public static  mainssder(String[] args) throws IOException {
        // Set up authentication
        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";
        String username = "your_username";
        String password = "your_password";
        AuthenticationType authType = AuthenticationType.MasterUser;

        Token token = TokenCredentials.getAccessToken(authType, clientId, clientSecret, username, password);
        TokenCredentials credentials = new TokenCredentials(token.getAccessToken());

        // Set up PowerBIClient
        RestClient restClient = new RestClient(credentials);
        PowerBIClient powerBiClient = new PowerBIClient(restClient);

        // Fetch a list of dashboards
        DashboardList dashboardList = powerBiClient.dashboards().getDashboards();
        for (Dashboard dashboard : dashboardList.value) {
            System.out.println(dashboard.displayName);
        }
    }



  public static void main(String[] args) {
        // Set up authentication
        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";
        String tenantId = "your_tenant_id";
        IAuthenticationProvider authProvider = new TokenCredentialAuthProvider(clientId, clientSecret, tenantId);

        // Set up GraphServiceClient
        GraphServiceClient graphClient = GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .buildClient();

        // Fetch a list of user profiles
        List<User> users = graphClient.users().buildRequest().get().getCurrentPage();

        for (User user : users) {
            System.out.println(user.displayName);
        }
    }


     public static jointsss() 
    {
        // Define the connection string
        String connectionString = "AuthType=OAuth;" +
            "Username=USERNAME;" +
            "Password=PASSWORD;" +
            "Url=https://YOUR_ORGANIZATION.crm.dynamics.com/;";

        // Create a CrmServiceClient object to connect to Dynamics 365
        CrmServiceClient crmServiceClient = new CrmServiceClient(connectionString);

        // Create an OrganizationServiceProxy object using the CrmServiceClient
        OrganizationServiceProxy serviceProxy = crmServiceClient.getOrganizationServiceProxy();

        // Create an OrganizationWebProxyClient object to use a proxy server
        OrganizationWebProxyClient webProxyClient = new OrganizationWebProxyClient("PROXY_SERVER_ADDRESS", "PROXY_SERVER_PORT", "USERNAME", "PASSWORD");

        // Use the web proxy client to create an OrganizationServiceStub object
        OrganizationServiceStub serviceStub = webProxyClient.getServiceStub();

        // Use the OrganizationServiceProxy to create a WhoAmIRequest
        WhoAmIRequest request = new WhoAmIRequest();

        // Execute the WhoAmIRequest using the OrganizationServiceStub
        Object response = serviceStub.execute(request);

        // Print the response
        System.out.println(response);
    }











