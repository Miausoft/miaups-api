# miausp-api

## How to run the app

1. Clone the repository. 


2. If you use Intellij, navigate to Run -> Edit Configurations and set `Active profiles` to `local`. The profile will use `application-local.properties` configurations inside the `resources` folder:
   * Migrations will be executed on a startup.
   * ngrok dashboard will be available at `http://localhost:4040`


3. Create the `.env` file inside the root directory. All required variables are defined in `.env.dist` which can be configured to your preferences.
