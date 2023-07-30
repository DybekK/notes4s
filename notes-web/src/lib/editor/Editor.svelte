<script lang="ts">
    import './styles.css'

    import StarterKit from '@tiptap/starter-kit'
    import {onMount} from 'svelte'
    import {Editor} from "@tiptap/core";
    import {editorStore} from '../editor.store'
    import {notesStore} from '../notes.store'

    const {editor} = editorStore
    const {notesState, updateNote} = notesStore

    let {notes, activeNoteId} = $notesState

    let element: Element
    onMount(() => {
        $editor = new Editor({
            element: element,
            editorProps: {
                attributes: {
                    class: 'focus:outline-none p-6 h-full w-full'
                },
            },
            extensions: [StarterKit],
            content: notes.find(note => note.id === activeNoteId)?.content,
            onUpdate: () => updateNote(activeNoteId, $editor.getHTML()),
            onTransaction: () => {
                $editor = $editor
            }
        })
    })
</script>

<div bind:this={element}/>