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
                    class: 'h-full p-6'
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

<div class="h-full w-full" bind:this={element}/>
