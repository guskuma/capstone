<h1>Notifique App (free and paid versions)</h1>
<h2>Build/Run configuration</h2>
Add following files to run app and gce backend:
<ul>
    <li>app/google-services.json (with app ids com.guskuma.notifique.free and com.guskuma.notifique.paid)</li>
    <li>gce/src/main/webapp/WEB-INF/serviceAccountKey.json</li>
</ul>
Both files can be downloaded from Firebase console.
Beware all versions of app (free and paid) should be added to firebase console in order to receive FCM messages.
<h2>API calls</h2>
Notifique backend module <b>GCE</b> is a REST API that uses Google Cloud Engine Endpoints API to communicate with Firebase and use FCM API. Backend can be started using gradle ":gce:appengineStart" task.
Calls to API shoud be done using HTTP POST with "Content-Type = 'Application/JSON'" to <u><i>http://{server-domain}/_ah/api/messages/v1/sendToTopic</i></u>
For the sake of simplicity and testing purpose, I have already added some code for all 3 types of requests:
<ul>
    <li>http://{server-domain}/_ah/api/messages/v1/information</li>
    <li>http://{server-domain}/_ah/api/messages/v1/report</li>
    <li>http://{server-domain}/_ah/api/messages/v1/warning</li>
</ul>
Also, cURL commands can be found in curl_examples file (pointing to localhost:8080).
*I have backend running at https://capstone-project-c681e.appspot.com/ for testing, fell free to use if needed*