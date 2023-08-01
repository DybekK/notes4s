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
			content: `test: <img src="https://source.unsplash.com/8xznAGy4HcY/800x400" />`
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
		store: notesState,
		updateNote
	}
}

export const notesStore = createNotesStore()
