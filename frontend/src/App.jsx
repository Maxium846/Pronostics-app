import { useEffect, useState } from 'react'
import { apiFetch } from './api/client'

function App() {
  const [status, setStatus] = useState('chargement...')

  useEffect(() => {
    apiFetch('/api/ping')
      .then((data) => setStatus(data.status))
      .catch((err) => setStatus(err.message))
  }, [])

  return (
    <main>
      <h1>Pronoleague</h1>
      <p>État de l'API : {status}</p>
    </main>
  )
}

export default App