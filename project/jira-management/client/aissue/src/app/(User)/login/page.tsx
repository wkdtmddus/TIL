'use client'

import axios from 'axios'
import Image from 'next/image'
import { useState, useEffect } from 'react'
import { useRouter } from 'next/navigation'
import { login } from '@/api/user/index'

export default function LoginPage() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [passwordError, setPasswordError] = useState<string>('')
  const [installPrompt, setInstallPrompt] = useState<Event | null>(null)
  const [isInstallable, setIsInstallable] = useState(false)
  const router = useRouter()

  useEffect(() => {
    const handleBeforeInstallPrompt = (event: Event) => {
      event.preventDefault()
      setInstallPrompt(event)
      setIsInstallable(true)
    }

    window.addEventListener('beforeinstallprompt', handleBeforeInstallPrompt)

    return () => {
      window.removeEventListener(
        'beforeinstallprompt',
        handleBeforeInstallPrompt,
      )
    }
  }, [])

  const handleInstallClick = async () => {
    if (installPrompt) {
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      ;(installPrompt as any).prompt()
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      const result = await (installPrompt as any).userChoice
      console.log(`사용자 선택: ${result.outcome}`)
      setInstallPrompt(null)
      setIsInstallable(false)
    }
  }

  const handleSignup = () => {
    router.push('/signup')
  }

  const handleLogin = () => {
    loginSubmit()
  }

  const loginSubmit = async () => {
    try {
      const res = await login(email, password)
      sessionStorage.setItem('accessToken', res.accessToken)
      sessionStorage.setItem('refreshToken', res.refreshToken)
      sessionStorage.setItem('memberId', res.memberId)
      sessionStorage.setItem('memberName', res.memberName)
      router.push('/project')
    } catch (error) {
      if (axios.isAxiosError(error)) {
        if (error.response?.data.message === '비밀번호가 일치하지 않습니다.') {
          setPasswordError(error.response?.data.message)
        } else if (
          error.response?.data.message === '존재하지 않는 이메일입니다.'
        ) {
          setPasswordError(error.response?.data.message)
        }
      }
    }
  }

  return (
    <div
      className="min-h-screen flex items-center justify-center bg-blue-100"
      onKeyDown={(e) => {
        if (e.key === 'Enter') {
          handleLogin()
        }
      }}
    >
      <div className="w-full max-w-md bg-white p-8 rounded-lg shadow-lg">
        <div className="flex flex-col items-center mb-6">
          <div className="flex items-center space-x-2">
            <Image
              src="/img/chatbot.png"
              alt="Logo"
              width={60}
              height={60}
              className="mb-4"
            />
            <h1 className="text-2xl font-bold text-teal-600 self-center">
              AIssue
            </h1>
          </div>
          <h2 className="text-lg" style={{ color: '#9EBDFF' }}>
            로그인
          </h2>
        </div>

        <div className="border-b border-gray-300 mb-6 w-full"></div>

        <div className="mb-4">
          {passwordError && (
            <p className="text-red-500 text-sm mb-2">{passwordError}</p>
          )}
          <input
            type="email"
            placeholder="Jira e-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded mb-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <div className="mb-6">
          <input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <button
          onClick={handleLogin}
          className="w-full bg-blue-400 text-white py-3 rounded mb-4 hover:bg-blue-500 transition-colors"
        >
          로그인
        </button>

        <button
          onClick={handleSignup}
          className="w-full border border-teal-500 text-teal-600 py-3 rounded hover:bg-teal-50 transition-colors"
        >
          회원가입
        </button>

        {isInstallable && (
          <button
            onClick={handleInstallClick}
            className="w-full bg-[#099488] text-white py-3 rounded mt-4 hover:bg-[#0DB9AB] transition-colors"
          >
            앱 설치
          </button>
        )}
      </div>
    </div>
  )
}
