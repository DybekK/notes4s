import { writable } from 'svelte/store'

export type Note = {
	id: number
	title: string
	content: string
}

function fixtureNotes(): Note[] {
	return [
		{
			id: 1,
			title: 'Note 1',
			content: `# Note 1`
		},
		{
			id: 2,
			title: 'Note 2',
			content: `# Note 2`
		}
	]
}

function createNotesStore() {
	const notesState = writable({
		activeNoteId: 1,
		notes: fixtureNotes()
	})

	const updateNote = (id: number, content: string) => {
		notesState.update((state) => {
			const note = state.notes.find((n) => n.id === id)
			if (note) {
				note.content = content
			}
			return state
		})
	}

	return {
		notesState,
		updateNote
	}
}

export const notesStore = createNotesStore()
