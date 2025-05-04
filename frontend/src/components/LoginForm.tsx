type LoginFormProps = {
  email: string;
  pin: string;
  handleLogin: (event: React.FormEvent<HTMLFormElement>) => void;
  setemail: (value: string) => void;
  setpin: (value: string) => void;
};

const LoginForm = ({ email, pin, handleLogin, setemail, setpin }: LoginFormProps) => {
  return (
    <div >
      <form onSubmit={handleLogin}>
        <div>
          email
          <input
            type="text"
            value={email}
            name="email"
            onChange={({ target }) => setemail(target.value)}
          />
        </div>
        <div>
          pin
          <input
            type="pin"
            value={pin}
            name="pin"
            onChange={({ target }) => setpin(target.value)}
          />
        </div>
        <button type="submit">login</button>
      </form>
    </div >
  )
}

export default LoginForm;