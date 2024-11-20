'use client'

import React, { useEffect, useState } from 'react'
import { usePathname } from 'next/navigation'
import {
  DragDropContext,
  Droppable,
  Draggable,
  DropResult,
} from '@hello-pangea/dnd'
import { getWeeklyStories } from '@/api/project'
import { useQuery } from '@tanstack/react-query'
import LoadingSpinner from '@/static/svg/blue-spinner.svg'
import IssueModal from '@/components/(Modal)/IssueModal/IssueModal'
import { updateIssueStatus } from '@/api/project'

interface Task {
  title: string
  start: Date
  end: Date
}

interface Story {
  id: number
  key: string
  title: string
  status: 'To Do' | 'In Progress' | 'Done'
  parent?: { id: string; summary: string }
  tasks: Task[]
}

interface Issue {
  id: number
  key: string
  title: string
  status: 'ToDo' | 'InProgress' | 'Done'
}

const WorkLogPage = () => {
  const pathname = usePathname()
  const projectId = pathname.split('/')[2]
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [selectedIssue, setSelectedIssue] = useState<Story | null>(null)
  const [categorizedIssues, setCategorizedIssues] = useState<{
    ToDo: Issue[]
    InProgress: Issue[]
    Done: Issue[]
  }>({
    ToDo: [],
    InProgress: [],
    Done: [],
  })

  const { isLoading, data } = useQuery({
    queryKey: ['weeklyStories', projectId],
    queryFn: () => getWeeklyStories(projectId),
  })

  useEffect(() => {
    if (data) {
      const stories: Story[] = data
      const issues: Issue[] = stories.map((story) => ({
        id: story.id,
        key: story.key,
        title: story.title,
        status: mapStatus(story.status),
      }))

      const categorized = {
        ToDo: issues.filter((issue) => issue.status === 'ToDo'),
        InProgress: issues.filter((issue) => issue.status === 'InProgress'),
        Done: issues.filter((issue) => issue.status === 'Done'),
      }

      setCategorizedIssues(categorized)
    }
  }, [data])

  function mapStatus(
    status: 'To Do' | 'In Progress' | 'Done',
  ): 'ToDo' | 'InProgress' | 'Done' {
    switch (status) {
      case 'To Do':
        return 'ToDo'
      case 'In Progress':
        return 'InProgress'
      case 'Done':
        return 'Done'
    }
  }

  const openModal = (issueId: number) => {
    if (data) {
      const story = data.find((story: Story) => story.id === issueId)
      if (story) {
        setSelectedIssue(story)
        setIsModalOpen(true)
      }
    }
  }

  const closeModal = () => {
    setSelectedIssue(null)
    setIsModalOpen(false)
  }

  const onDragEnd = (result: DropResult) => {
    const { source, destination } = result

    if (!destination) return

    const sourceDroppableId = source.droppableId as
      | 'ToDo'
      | 'InProgress'
      | 'Done'
    const destinationDroppableId = destination.droppableId as
      | 'ToDo'
      | 'InProgress'
      | 'Done'

    const updatedIssues = { ...categorizedIssues }
    const sourceItems = Array.from(updatedIssues[sourceDroppableId])
    const [movedItem] = sourceItems.splice(source.index, 1)

    if (sourceDroppableId === destinationDroppableId) {
      sourceItems.splice(destination.index, 0, movedItem)
      updatedIssues[sourceDroppableId] = sourceItems
    } else {
      movedItem.status = destinationDroppableId
      const destinationItems = Array.from(updatedIssues[destinationDroppableId])
      destinationItems.splice(destination.index, 0, movedItem)

      updatedIssues[sourceDroppableId] = sourceItems
      updatedIssues[destinationDroppableId] = destinationItems
    }

    setCategorizedIssues(updatedIssues)

    updateIssueStatus(
      movedItem.key,
      mapStatusToApiFormat(destinationDroppableId),
    )
      .then(() => {
        console.log(`이슈 ${movedItem.key} 업데이트 ${destinationDroppableId}`)
      })
      .catch((error) => {
        console.error('이슈 업데이트 실패:', error)
        // 지라 api를 이용한 이슈 업데이트 실패시 UI를 원래대로 복구
        setCategorizedIssues((prevIssues) => {
          const revertedIssues = { ...prevIssues }
          if (sourceDroppableId === destinationDroppableId) {
            sourceItems.splice(source.index, 0, movedItem)
          } else {
            revertedIssues[destinationDroppableId] = revertedIssues[
              destinationDroppableId
            ].filter((issue) => issue.key !== movedItem.key)
            revertedIssues[sourceDroppableId].splice(source.index, 0, movedItem)
          }
          return revertedIssues
        })
      })
  }

  function mapStatusToApiFormat(
    status: 'ToDo' | 'InProgress' | 'Done',
  ): 'To Do' | 'In Progress' | 'Done' {
    switch (status) {
      case 'ToDo':
        return 'To Do'
      case 'InProgress':
        return 'In Progress'
      case 'Done':
        return 'Done'
    }
  }

  if (isLoading)
    return (
      <div className="flex justify-center items-center h-full w-full">
        <div className="flex flex-col justify-center items-center gap-y-3">
          <LoadingSpinner className="animate-spin" />
          <p className="font-bold">전체 업무 로그를 불러오는 중입니다.</p>
        </div>
      </div>
    )

  return (
    <div className="flex min-h-screen bg-gray-100 flex-col lg:flex-row">
      <div className="w-full lg:w-4/5 p-4 lg:p-8">
        <div className="mb-6">
          <h1 className="text-xl lg:text-2xl font-bold mb-2">
            {projectId} 프로젝트 스프린트 일정
          </h1>
        </div>

        <DragDropContext onDragEnd={onDragEnd}>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
            {(['ToDo', 'InProgress', 'Done'] as const).map((status) => {
              let headerStyle = ''
              let bgStyle = ''
              if (status === 'ToDo') {
                headerStyle = 'text-[#33675F]'
                bgStyle = 'bg-[#B2E0D9]'
              } else if (status === 'InProgress') {
                headerStyle = 'text-[#F60000]'
                bgStyle = 'bg-[#FACACA]'
              } else if (status === 'Done') {
                headerStyle = 'text-[#000000]'
                bgStyle = 'bg-[#C0C0C0]'
              }

              return (
                <Droppable key={status} droppableId={status}>
                  {(provided, snapshot) => (
                    <div
                      ref={provided.innerRef}
                      {...provided.droppableProps}
                      className={`p-4 rounded-lg shadow-md ${bgStyle} flex flex-col ${
                        snapshot.isDraggingOver
                          ? 'border-2 border-blue-400'
                          : ''
                      }`}
                      style={{ minHeight: '200px' }}
                    >
                      <h2
                        className={`text-base lg:text-xl font-semibold mb-4 text-center ${headerStyle}`}
                      >
                        {status}
                      </h2>
                      <div className="space-y-4 flex-1 px-2 pb-2">
                        {categorizedIssues[status].map((issue, index) => (
                          <Draggable
                            key={issue.key}
                            draggableId={issue.key}
                            index={index}
                          >
                            {(provided) => (
                              <div
                                ref={provided.innerRef}
                                {...provided.draggableProps}
                                {...provided.dragHandleProps}
                                className="bg-white p-4 rounded-lg shadow-sm flex items-center justify-between"
                                style={{
                                  ...provided.draggableProps.style,
                                }}
                                onClick={() => openModal(issue.id)}
                              >
                                <div>
                                  <h3 className="font-semibold text-sm lg:text-base text-gray-800">
                                    {issue.title}
                                  </h3>
                                  <p className="text-xs lg:text-sm text-gray-500">
                                    {issue.key}
                                  </p>
                                </div>
                                <img
                                  src="/img/avatar.png"
                                  alt="Avatar"
                                  className="w-4 h-4 lg:w-6 lg:h-6"
                                />
                              </div>
                            )}
                          </Draggable>
                        ))}
                        {provided.placeholder}
                      </div>
                    </div>
                  )}
                </Droppable>
              )
            })}
          </div>
        </DragDropContext>

        {selectedIssue && (
          <IssueModal
            isOpen={isModalOpen}
            onClose={closeModal}
            tasks={selectedIssue?.tasks || []}
            title={selectedIssue?.title || ''}
            parentSummary={selectedIssue?.parent?.summary || ''}
            issueId={selectedIssue?.id || null}
            issueKey={selectedIssue?.key || null}
            parentIssueId={selectedIssue?.parent?.id || ''}
          />
        )}
      </div>
    </div>
  )
}

export default WorkLogPage
