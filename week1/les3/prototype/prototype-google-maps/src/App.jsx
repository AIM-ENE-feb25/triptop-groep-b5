import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <iframe
        title="Google Maps view of Space Needle, Seattle WA"
        width="600"
        height="450"
        style={{ border: 0 }}
        loading="lazy"
        allowFullScreen
        referrerPolicy="no-referrer-when-downgrade"
        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCYXC-hg8uekGhzT5tyS3jhal7sL-2RVSw&q=Space+Needle,Seattle+WA">
      </iframe>
    </>
  )
}

export default App
