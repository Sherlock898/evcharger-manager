import { useState } from 'react';
import './App.css';
import ChargingStations from './components/chargingStations';
import LoginForm from './components/LoginForm';
import Notification from './components/Notification';
import UserPanel from './components/UserPanel';
import loginService from './services/loginService';

type UserType = {
  token: string;
  tokenType: string;
}
function App() {
  const [currentView, setCurrentView] = useState<string>('stations');
  const [email, setemail] = useState<string>('');
  const [pin, setpin] = useState<string>('');
  const [user, setUser] = useState<UserType | null>(null)
  const [errorMessage, setErrorMessage] = useState<string | null>('')
  const [typeErrorMessage, setTypeErrorMessage] = useState<string | null>('success')

  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()

    try {
      const user = await loginService.login({
        email, pin,
      })
      setUser(user)
      setemail('')
      setpin('')
    } catch (exception) {
      setErrorMessage('Wrong credentials')
      setTypeErrorMessage('error')
      setTimeout(() => {
        setErrorMessage(null)
        setTypeErrorMessage(null)
      }, 5000)
    }
  }

  return (
    <>
      <Notification message={errorMessage} type={typeErrorMessage === 'error' ? 'error' : 'success'} />
      {user === null ? (
        <LoginForm email={email} pin={pin} handleLogin={handleLogin} setemail={setemail} setpin={setpin} />
      ) : (
        <div className="dashboard">
          <UserPanel user={user} />
          <Sidebar setView={setCurrentView} />
          <main className="main-content">
            {currentView === 'stations' && (
              <ChargingStations
                token={user.token}
                setErrorMessage={setErrorMessage}
                setTypeErrorMessage={setTypeErrorMessage}
              />
            )}
          </main>
        </div>
      )}
    </>
  )
}

export default App
