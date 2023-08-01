import { writable } from 'svelte/store'
import type { Editor, HTMLContent } from '@tiptap/core'

function createEditorStore() {
	const editor = writable<Editor>()

	const setEditorContext = (content: HTMLContent) => {
		editor.update((e) => {
			e?.commands.setContent(content)
			return e
		})
	}

	return {
		store: editor,
		setEditorContext
	}
}

export const editorStore = createEditorStore()
