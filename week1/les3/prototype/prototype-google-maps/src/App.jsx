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
        textQuery: search,
        languageCode: 'nl',
        regionCode: '',
        rankPreference: 0,
        includedType: '',
        openNow: true,
        minRating: 0,
        maxResultCount: 20,
        priceLevels: [],
        strictTypeFiltering: true,
        locationBias: {
          circle: {
            center: {
              latitude: 51.983333,
              longitude: 5.916667
            },
            radius: 20000
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
        placeholder="Zoeken..."
        value={search}
        onChange={(event) => setSearch(event.target.value)}
        className="border rounded p-2 mb-4 me-4"
      />
      <button
        onClick={() => query(search)}
      >
        Zoeken
      </button>
      <ul className="mt-4 space-y-4">
        {results?.places?.map((result, index) => (
          <li key={index}>
            <div className="flex flex-col">
              <span className="font-bold text-lg" style={{ color: result.iconBackgroundColor }}>{result.displayName.text}</span>
              <a
                href={result.googleMapsLinks.placeUri}
                target="_blank"
              >
                {result.formattedAddress}
              </a>
            </div>
          </li>
        ))}
      </ul>
    </>
  )
}

export default App
