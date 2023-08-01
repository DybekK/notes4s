<script lang="ts">
    import './styles.css'

    import {onMount} from 'svelte'
    import StarterKit from '@tiptap/starter-kit'
    import Image from '@tiptap/extension-image'
    import Typography from '@tiptap/extension-typography'
    import Underline from '@tiptap/extension-underline'
    import {Editor} from "@tiptap/core";
    import {editorStore} from '../editor.store'
    import {notesStore} from '../notes.store'

    const {store: editor} = editorStore
    const {store: notesState, updateNote} = notesStore

    let {notes, activeNoteId} = $notesState

    let element: Element
    onMount(() => {
        $editor = new Editor({
            element: element,
            editorProps: {
                attributes: {
                    class: 'h-full focus:outline-none p-6 dark:bg-gray-800 dark:text-gray-200'
                },
            },
            extensions: [
                Image,
                Typography,
                Underline,
                StarterKit
            ],
            content: notes.find(note => note.id === activeNoteId)?.content,
            onUpdate: () => updateNote(activeNoteId, $editor.getHTML()),
            onTransaction: () => {
                $editor = $editor
            }
        })
    })
</script>

<div class="h-full w-full" bind:this={element}/>
