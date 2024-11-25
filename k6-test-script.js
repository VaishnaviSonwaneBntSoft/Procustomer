import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10, // Number of virtual users
  duration: '30s', // Test duration
};

export default function () {
  const url = 'https://localhost:8080/api/core-identity'; // Replace with your API endpoint
  const payload = JSON.stringify({
    "customerNumber": 987654324,
    "tenantName": "Global Tech Solutions",
    "firstName": "Rahul",
    "middleName1": "karuk",
    "middleName2": "",
    "middleName3": "",
    "familySurname": "Sroe",
    "dateOfBirth": "1990-11-15",
    "placeOfBirth": "New York",
    "mothersMaidenName": "Smith"
 }); // Replace with your payload
  const params = { headers: { 'Content-Type': 'application/json' } };

  const response = http.post(url, payload, params);

  check(response, {
    'status is 200': (r) => r.status === 200,
    'response time is < 200ms': (r) => r.timings.duration < 200,
  });

  sleep(1); // Pause between iterations
}
