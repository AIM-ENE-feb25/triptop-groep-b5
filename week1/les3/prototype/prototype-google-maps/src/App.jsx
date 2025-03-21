import { useState } from 'react'
import './App.css'

function App() {
  const [search, setSearch] = useState('')
  const [results, setResults] = useState({})

  const query = () => {
    const url = 'https://google-map-places-new-v2.p.rapidapi.com/v1/places:searchText';
    const options = {
      method: 'POST',
      headers: {
        'x-rapidapi-key': '262b586635msh92707a41bb69d42p16a7eajsn1d5caaa53bfa',
        'x-rapidapi-host': 'google-map-places-new-v2.p.rapidapi.com',
        'Content-Type': 'application/json',
        'X-Goog-FieldMask': '*'
      },
      body: JSON.stringify({
        textQuery: 'restaurants',
        languageCode: '',
        regionCode: '',
        rankPreference: 0,
        includedType: '',
        openNow: true,
        minRating: 0,
        maxResultCount: 1,
        priceLevels: [],
        strictTypeFiltering: true,
        locationBias: {
          circle: {
            center: {
              latitude: 40,
              longitude: -110
            },
            radius: 10000
          }
        },
        evOptions: {
          minimumChargingRateKw: 0,
          connectorTypes: []
        }
      })
    };

    fetch(url, options)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setResults(data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  }

  return (
    <>
      <input
        type="text"
        placeholder="Search..."
        value={search}
        onChange={(event) => setSearch(event.target.value)}
      />
      <button onClick={() => query(search)}>Search</button>
      {/* <p>Results: {results}</p> */}
    </>
  )
}

export default App
